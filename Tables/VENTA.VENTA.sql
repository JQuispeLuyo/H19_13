CREATE TABLE [VENTA].[VENTA]
(
[IDVENT] [int] NOT NULL IDENTITY(1, 1),
[IDEMP] [int] NULL,
[FECVEN] [date] NULL
) ON [PRIMARY]
GO
ALTER TABLE [VENTA].[VENTA] ADD CONSTRAINT [PK__VENTA__EA675545642CD13D] PRIMARY KEY CLUSTERED  ([IDVENT]) ON [PRIMARY]
GO
