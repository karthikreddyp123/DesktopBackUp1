CREATE TABLE [dbo].[user](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[role] [varchar](255) NULL,
 CONSTRAINT [PK_USER] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)
) ON [PRIMARY]