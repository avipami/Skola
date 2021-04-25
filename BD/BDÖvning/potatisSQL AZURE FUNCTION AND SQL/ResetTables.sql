DROP TABLE DhtMeasurements
DROP TABLE Devices
DROP TABLE DeviceModels
DROP TABLE GeoLocations
DROP TABLE DeviceVendors
DROP TABLE SensorTypes
DROP TABLE TemperatureAlerts
DROP TABLE TimeTable


CREATE TABLE TimeTable
(
	[TimeTableId]	int identity (1,1) primary key,
	[UnixUtcTime]	bigINT not null,
	[UtcDateTime]	AS DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00'), 
	[Date]			AS CONVERT(DATE, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')), 
	[Year]			AS DATEPART(YEAR, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),  
	[Quarter]		AS DATEPART(QUARTER, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[QuarterName]	AS CONVERT(CHAR(2), CASE DATEPART(QUARTER, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')) WHEN 1 THEN 'Q1' WHEN 2 THEN 'Q2' WHEN 3 THEN 'Q3' WHEN 4 THEN 'Q4' END),  
	[Month]			AS DATEPART(MONTH, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),  
	[MonthName]		AS DATENAME(MONTH, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[WeekdayOfMonth]AS DATEPART(DAY, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[WeekdayName]	AS DATENAME(WEEKDAY, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[DayOfWeek]		AS DATEPART(WEEKDAY, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[DayOfYear]		AS DATEPART(DAYOFYEAR, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[Hour]			AS DATEPART(HOUR, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[Minute]		AS DATEPART(MINUTE, DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00')),
	[Second]		AS DATEPART(SECOND,	DATEADD(S, [UnixUtcTime], '1970-01-01 00:00:00'))
)

CREATE TABLE [TemperatureAlerts] (
  [TempAlertsId] int identity(1,1) primary key,
  [Status] int
);

CREATE TABLE [SensorTypes] (
  [SensorTypeId] int identity(1,1) primary key,
  [SensorName] nvarchar(50)
);
CREATE TABLE [DeviceVendors] (
  [VendorId] int identity(1,1) primary key,
  [VendorName] nvarchar(50)
);

GO
CREATE TABLE [DeviceModels] (
  [DevModelsId] int identity(1,1) primary key,
  [ModelName] nvarchar(50),
  [VendorId] int references DeviceVendors(VendorId)
);

CREATE TABLE [GeoLocations] (
  [GeoLocId] bigint identity(1,1) primary key,
  [Latitude] nvarchar(50),
  [Longitude] nvarchar(50)
);
GO
CREATE TABLE [Devices] (
  [DevicesId] bigint identity(1,1) primary key,
  [DeviceName] nvarchar(50),
  [SensorTypeId] int references SensorTypes(SensorTypeId),
  [GeoLocId] bigint references GeoLocations (GeoLocId),
  [DevModelsId] int references DeviceModels (DevModelsId)
);
GO
CREATE TABLE [DhtMeasurements] (
  [DhtMeasurementId] int identity(1,1) primary key,
  [DevicesId] bigint references Devices (DevicesId),
  [TimeTableId] int references TimeTable (TimeTableId),
  [Temperature] float,
  [Humidity] float,
  [TempAlertsId] int references TemperatureAlerts (TempAlertsId)
);







CREATE TABLE TemperatureAlerts (
	Id int not null identity(1,1) primary key,
	Status int not null unique
)

CREATE TABLE SensorTypes (
	Id int not null identity(1,1) primary key,
	SensorName nvarchar(50) not null unique
)

CREATE TABLE DeviceVendors (
	Id int not null identity(1,1) primary key,
	VendorName nvarchar(50) not null unique
)

CREATE TABLE GeoLocations (
	Id bigint not null identity(1,1) primary key,
	Latitude nvarchar(50) not null,
	Longitude nvarchar(50) not null
)
GO

CREATE TABLE DeviceModels (
	Id int not null identity(1,1) primary key,
	ModelName nvarchar(50) not null unique,
	VendorId int not null references DeviceVendors(Id)
)
GO

CREATE TABLE Devices (
	Id bigint not null identity(1,1) primary key,
	DeviceName nvarchar(50) not null unique,
	SensorTypeId int not null references SensorTypes(Id),
	GeoLocationId bigint not null references GeoLocations(Id),
	ModelId int not null references DeviceModels(Id),
)
GO

CREATE TABLE DhtMeasurements (
	Id bigint not null identity(1,1) primary key,
	DeviceId bigint not null references Devices(Id),
	MeasureUnixTime int not null references TimeTable(UnixUtcTime),
	Temperature float not null,
	Humidity float not null,
	TemperatureAlert int not null references TemperatureAlerts(Id)
)