DROP TABLE FactMeasurements
DROP TABLE [dbo].[DimDevices]
DROP TABLE [dbo].[DimGeoLocations]
DROP TABLE [dbo].[DimTimeTable]
DROP TABLE [dbo].[DimTemperatureAlerts]

CREATE TABLE [DimTimeTable] (
  [TIMEId] bigint primary key,
  [UtcDateTime] datetime,
  [Date] date,
  [Year] int,
  [Month] int,
  [Day] int,
  [Hours] int,
  [Minutes] int,
  [Seconds] int
);

CREATE TABLE [DimGeoLocations] (
  [GEOId] bigint primary key,
  [Latitude] nvarchar(50),
  [Longitude] nvarchar(50)
);

CREATE TABLE [DimTemperatureAlerts] (
  [TEMPALERTId] int primary key,
  [Status] int
);

CREATE TABLE [DimDevices] (
  [DEVICEId] bigint primary key,
  [DeviceName] nvarchar(50),
  [SensorName] nvarchar(50),
  [VendorName] nvarchar(50)
);

GO

CREATE TABLE [FactMeasurements] (
  MeasurementsId int identity (1,1) primary key,
  DimDevices bigint references DimDevices(DEVICEId),
  DimTimeTable bigint references DimTimeTable(TIMEId),
  Temperature float,
  Humidity float,
  DimTemperatureAlerts int references DimTemperatureAlerts(TEMPALERTId),
  DimGeoLocations bigint references DimGeoLocations(GEOId)
);
