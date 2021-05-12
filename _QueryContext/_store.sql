CREATE PROC ADDNhanVien
(@mNV INT , @Ht NVARCHAR(50),@gt NVARCHAR(5), @QQ NVARCHAR(50), @BP NVARCHAR(50))
AS 
	INSERT INTO dbo.NhanVien
	(
	    MaNV,
	    HoTen,
	    GioiTinh,
	    QueQuan,
	    NgaySinh,
	    NgayVaolam,
	    BoPhan
	)
	VALUES
	(   @mNV,         -- MaNV - int
	    @Ht,       -- HoTen - nvarchar(50)
	    @gt,       -- GioiTinh - nvarchar(5)
	    @QQ,       -- QueQuan - nvarchar(50)
	    GETDATE(), -- NgaySinh - date
	    GETDATE(), -- NgayVaolam - date
	    @BP       -- BoPhan - nvarchar(50)
	    )

ADDNhanVien 05,'Nguyễn Duy Anh','Nam','HCM','Nhân Viên'

GO
 SELECT * FROM dbo.NhanVien

 SELECT * FROM dbo.HoaDon

GO
CREATE PROC AddFood (@MaFood INT,@MaBi VARCHAR(8))
AS
INSERT INTO dbo.Instance_ThucDon
(
    IDBill,
    MaMon
)
VALUES
(   @MaBi, -- IDBill - varchar(8)
    @MaFood   -- MaMon - int
    )

	GO
    SELECT * FROM dbo.Instance_ThucDon

	ALTER TABLE dbo.Instance_ThucDon DROP COLUMN ID

	GO
    ALTER TABLE dbo.Instance_ThucDon ADD IDC int NOT NULL IDENTITY(1,1) PRIMARY key