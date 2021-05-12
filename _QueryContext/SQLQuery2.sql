SELECT SUM(gia) as Moneyddddd FROM dbo.HoaDon ,dbo.Instance_ThucDon,dbo.ThucDon WHERE  
HoaDon.IDBill = N'aa1'
AND Instance_ThucDon.IDBill = HoaDon.IDBill
AND Instance_ThucDon.MaMon = ThucDon.MaMon 
GO

SELECT TenMon FROM dbo.HoaDon ,dbo.Instance_ThucDon,dbo.ThucDon WHERE  
HoaDon.IDBill = N'AB1'
AND Instance_ThucDon.IDBill = HoaDon.IDBill
AND Instance_ThucDon.MaMon = ThucDon.MaMon 
GO

SELECT * FROM dbo.HoaDon
SELECT * FROM dbo.Instance_ThucDon
SELECT * FROM dbo.Instance_People
SELECT * FROM dbo.People







DECLARE @MoneyPay INT;
EXEC dbo.Money_pay @idbill = 'LA04',                -- varchar(8)
                   @MoneyPay = @MoneyPay OUTPUT -- int


CREATE PROC Money_pay (@idbill VARCHAR(8) , @MoneyPay INt output)
AS
BEGIN
SELECT SUM(gia) AS Money FROM dbo.HoaDon,dbo.Instance_People ,dbo.Instance_ThucDon,dbo.ThucDon,dbo.People WHERE  
HoaDon.IDBill = @idbill AND		
Instance_People.ID = People.ID
AND Instance_ThucDon.IDBill = Instance_People.IDBill
AND HoaDon.IDBill = Instance_People.IDBill
AND Instance_ThucDon.IDBill = HoaDon.IDBill
AND Instance_ThucDon.MaMon = ThucDon.MaMon ;
SELECT @MoneyPay = @@RowCount
END


-- fix nó lại được rồi đó tại vì something  lỗi và đẫ được fix ở đợt code này



-- Hàm này cần 2 thứ input là mã bill và tên hóa đơn
-- đổi cái cụm select thành Sum là ra tổng giá tiền. cứ vậy select thôi còn nếu muốn tính tiền cái gì đó thì change cái đỏ đỏ sẽ ra kq

SELECT * FROM dbo.People

SELECT * FROM dbo.HoaDon

GO
CREATE PROC InforBill
(@idbill VARCHAR(8), @name NVARCHAR(50), @tongtien int OUTPUT)
AS
BEGIN
    SELECT SUM(Gia)  FROM dbo.HoaDon,dbo.Instance_People ,dbo.Instance_ThucDon,dbo.ThucDon,dbo.NhanVien,dbo.People WHERE  
HoaDon.IDBill = @idbill AND
Instance_People.ID = People.ID
AND Instance_ThucDon.IDBill = Instance_People.IDBill
AND HoaDon.IDBill = Instance_People.IDBill
AND Instance_ThucDon.IDBill = HoaDon.IDBill
AND HoaDon.MaNV = NhanVien.MaNV
AND Name = @name;
SELECT @tongtien = @@ROWCOUNT;
END

DECLARE @tongtien INT;
EXEC dbo.InforBill @idbill = 'LA01',                -- varchar(8)
                   @name = N'Nguyễn Thùy Trang',                 -- nvarchar(50)
                   @tongtien = @tongtien OUTPUT -- int
				   GO
CREATE PROC InforBillFood
(@idbill VARCHAR(8), @name NVARCHAR(50), @tongtien int OUTPUT)
AS
BEGIN
    SELECT HoaDon.IDBill,TenMon,Gia FROM dbo.HoaDon,dbo.Instance_People ,dbo.Instance_ThucDon,dbo.ThucDon,dbo.NhanVien,dbo.People WHERE  
HoaDon.IDBill = @idbill AND
Instance_People.ID = People.ID
AND Instance_ThucDon.IDBill = Instance_People.IDBill
AND HoaDon.IDBill = Instance_People.IDBill
AND Instance_ThucDon.IDBill = HoaDon.IDBill
AND HoaDon.MaNV = NhanVien.MaNV
AND Name = @name
SELECT @tongtien = @@ROWCOUNT;
END

DECLARE @tongtien INT;
EXEC dbo.InforBillFood @idbill = 'LA01',                -- varchar(8)
                       @name = N'Nguyễn Thùy Trang',                 -- nvarchar(50)
                       @tongtien = @tongtien OUTPUT -- int


SELECT * FROM dbo.Ban 

GO
CREATE PROC ADDBan (@maban VARCHAR(8), @sc INT, @tt BIT)
AS
INSERT dbo.Ban
(
    MaBan,
    SucChua,
    TinhTrang
)
VALUES
(   @maban,  -- MaBan - varchar(10)
    @sc,   -- SucChua - int
    @tt -- TinhTrang - bit
    )

	GO
 ADDban 'AB16',5,1
GO

SELECT MaMon,TenMon,Gia,TinhNang,ThoiDiemBan FROM dbo.ThucDon 

SELECT * FROM dbo.HoaDon

SELECT * FROM dbo.Ban

SELECT * FROM dbo.Instance_ThucDon

SELECT * FROM dbo.People

SELECT * FROM dbo.Instance_People

SELECT * FROM dbo.People

SELECT * FROM dbo.NhanVien

INSERT dbo.Instance_People
(
    IDBill
)
VALUES
(   
    'LA03' -- IDBill - varchar(8)
    )
	GO


-- input: IDBILL , mã nhân viên có thể tự nè, mã món ( cái này có list thì dùng list tạo là xong) mã bàn , tên người dùng
-- Tên người dùng thì lúc input vào nếu nó tồn tại rồi thì khỏi cần create mà nếu không thì create
-- mã nhân viên thì auto vậy nên cái này không cần insert 
-- mã món có list rồi nên nó sẽ tạo theo danh sách list và sẽ phải insert cùng với mã bill tự tạo.
-- mã bàn thì chắc k cần..
-- >>> vậy thì từ đây cần tạo 2 cái insert. 1. Instance_ThucDon || 2. HoaDon

CREATE PROC CreateHD1(@idbill VARCHAR(8), @name NVARCHAR(50), @maban VARCHAR(10),@manv INT )
AS
INSERT dbo.People
(
   
    Name,
    Age
)
VALUES
(
    @name, -- Name - nvarchar(50)
    21    -- Age - int
    )
INSERT dbo.HoaDon
(
    IDBill,
    MaNV,
    MaBan
)
VALUES
(   @idbill, -- IDBill - varchar(8)
    @manv,  -- MaNV - int
    @maban -- MaBan - varchar(10)
    )    
INSERT dbo.Instance_People
(
    IDBill
)
VALUES
(   
    @idbill -- IDBill - varchar(8)
    )

GO

CREATE PROC IN_Thucdon (@idbill VARCHAR(8),@mamon INT)	
AS
INSERT dbo.Instance_ThucDon
(
    IDBill,
    MaMon
)
VALUES
(   @idbill, -- IDBill - varchar(8)
    @mamon   -- MaMon - int
    )
GO

dbo.IN_Thucdon @idbill = '', -- varchar(8)
               @mamon = 0    -- int
			   GO
dbo.ThucDon_HoaDon @idbill = '', -- varchar(8)
                   @mamon = 0,   -- int
                   @maban = '',  -- varchar(10)
                   @manv = 0     -- int


				   GO
SELECT * FROM dbo.DangNhap

INSERT dbo.DangNhap
(
    UserName,
    PassWord
)
VALUES
(   'thang', -- UserName - varchar(32)
    '123'  -- PassWord - varchar(32)
    )
	GO
 SELECT * FROM dbo.ThucDon WHERE TenMon Like N'%Cam%'
 SELECT * FROM dbo.Ban WHERE TinhTrang = 1
 DELETE dbo.Ban WHERE MaBan = N'AB003'

GO
IN_Thucdon N'LA04',2
	GO
	CreateHD N'LA04',N'Thắng',N'AB013',1


GO

INSERT dbo.NhanVien
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
(   1,         -- MaNV - int
    N'Thang',       -- HoTen - nvarchar(50)
    N'nu',       -- GioiTinh - nvarchar(5)
    N'HL',       -- QueQuan - nvarchar(50)
    GETDATE(), -- NgaySinh - date
    GETDATE(), -- NgayVaolam - date
    N'King '        -- BoPhan - nvarchar(50)
    )

SELECT * FROM dbo.NhanVien WHERE QueQuan = N'HL'

SELECT * FROM dbo.ThucDon
INSERT dbo.Ban
(
    MaBan,
    SucChua,
    TinhTrang
)
VALUES
(   '999',  -- MaBan - varchar(10)
    2,   -- SucChua - int
    1 -- TinhTrang - bit
    )

SELECT * FROM dbo.Ban

INSERT dbo.HoaDon
(
    IDBill,
    MaNV,
    MaBan
)
VALUES
(   'LA01', -- IDBill - varchar(8)
    1,  -- MaNV - int
    'AB011'  -- MaBan - varchar(10)
    )

	SELECT * FROM dbo.HoaDon
	SELECT * FROM dbo.Instance_ThucDon
	SELECT * FROM dbo.Ban

GO
dbo.CreateHD @idbill = 'aa1', -- varchar(8)
             @name = N'NG',  -- nvarchar(50)
             @maban = 'AB17',  -- varchar(10)
             @manv = 1     -- int
