
SELECT * FROM dbo.Instance_People
ALTER TABLE dbo.Instance_ThucDon ADD FOREIGN KEY (MaMon) REFERENCES dbo.ThucDon(MaMon)

SELECT * FROM dbo.Instance_ThucDon
