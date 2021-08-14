/*
This script generates a update trigger where you have to insert the updated row in another table like an audit table only if any one of the column was changed. 
*/

DECLARE @tablename VARCHAR(100)
DECLARE @audittable VARCHAR(100)
DECLARE @sqlInsert VARCHAR(MAX)
DECLARE @sqlColumns VARCHAR(MAX)
DECLARE @sqlJoin VARCHAR(MAX)
DECLARE @sqlWhere VARCHAR(MAX)
DECLARE @sqlWhereFinal VARCHAR(MAX)
DECLARE @sqlHeader VARCHAR(MAX)
DECLARE @quote CHAR(1)
SET @quote = CHAR(39)

SET @tablename = 'Trigger Table Name'        --Replace this with the table name 
SET @audittable = 'Audit Table Name'        --Replace this with the audit table to insert the changed data

--this is just the header info  for the trigger
SET @sqlHeader = 'IF OBJECT_ID('+@quote+''+@tablename+'_U'+@quote+') IS NOT NULL
       DROP TRIGGER dbo.'+@tablename+'_U
GO

CREATE TRIGGER dbo.'+@tablename+'_U
ON dbo.'+@tablename+' FOR update
AS '
PRINT @sqlHeader

--select insert into
SELECT @sqlInsert = COALESCE(@sqlInsert+' ,' , '') + name + CHAR(13)+ CHAR(9)  FROM sys.syscolumns WHERE OBJECT_NAME(id) = @tablename ORDER BY colid
SET @sqlInsert = 'insert into dbo.'+@audittable+'('+CHAR(13) +CHAR(9)+@sqlInsert +')'
PRINT @sqlInsert

-- select col list
SELECT @sqlColumns = COALESCE(@sqlColumns+' ,' , '') +'d.'+ name + CHAR(13) + CHAR(9) FROM sys.syscolumns WHERE OBJECT_NAME(id) = @tablename ORDER BY colid
SET @sqlColumns = 'select '+CHAR(13) +CHAR(9)+ @sqlColumns 

--strip the last linebreak
SET @sqlColumns = LEFT(@sqlColumns, (LEN(@sqlColumns)-2))
PRINT @sqlColumns

--generate the join condition between Inserted and Deleted tables if the table has Primary key
IF EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE   WHERE table_name  = @tablename AND constraint_name LIKE '%PK%')
BEGIN
               SET @sqlJoin = ''
               SELECT @sqlJoin = COALESCE(@sqlJoin , '') + 'd.'+ column_name + ' = i.'+ column_name + CHAR(13)+CHAR(9) +' and ' FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE table_name = @tablename AND constraint_name LIKE '%PK%'
               SET @sqlJoin = 'from ' + CHAR(13) + CHAR(9) + ' deleted d join inserted i on ' + @sqlJoin 
               --strip off the last 'and'
               SET @sqlJoin = LEFT(@sqlJoin, (LEN(@sqlJoin)-6))
END
ELSE
       SET @sqlJoin = 'from deleted d, inserted i'

PRINT @sqlJoin

--generate the != clause where you check if atleast one column is changed...
DECLARE @coltype VARCHAR(100)
DECLARE @colname VARCHAR(100)
SET @sqlWhereFinal = 'where'  

DECLARE colcursor CURSOR LOCAL FORWARD_ONLY  READ_ONLY FOR SELECT st.name, sc.name
FROM sys.syscolumns sc JOIN sys.systypes st ON sc.xtype = st.xtype 
WHERE OBJECT_NAME(sc.id) = @tablename AND sc.name NOT IN
(SELECT column_name FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE   WHERE table_name  = @tablename AND constraint_name LIKE '%PK%')

OPEN colcursor
FETCH next FROM colcursor INTO @coltype , @colname
WHILE @@fetch_status = 0
BEGIN
       SET @sqlWhere = ''
       PRINT @sqlWhereFinal
       SET @sqlWhereFinal         = ''
       SET @sqlWhere = CASE WHEN @coltype IN('smalldatetime','datetime','sql_variant','ntext','varbinary','varchar','binary','char','timestamp','nvarchar','nchar','xml','sysname')
                                               THEN  @sqlWhere + CHAR(9) + 'isnull(d.'+ @colname +','''') != isnull(i.'+ @colname + ','''') or'  
                                               ELSE
                                                          @sqlWhere + CHAR(9) + 'isnull(d.'+ @colname +',-1) != isnull(i.'+ @colname + ',-1) or'
                                               END
       SET @sqlWhereFinal = @sqlWhereFinal + @sqlWhere 
       FETCH next FROM colcursor INTO @coltype , @colname
END
CLOSE colcursor
DEALLOCATE colcursor

--remove the last 'or'
SET @sqlWhereFinal = LEFT(@sqlWhereFinal, (LEN(@sqlWhereFinal)-3))
PRINT @sqlWhereFinal