CREATE TABLE Cliente (
    idCliente BIGINT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion VARCHAR(255)
);

CREATE TABLE Cuenta (
    idCuenta BIGINT PRIMARY KEY,
    numeroCuenta VARCHAR(255),
    saldo DOUBLE,
    titular VARCHAR(255),
    idCliente BIGINT,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE CuentaAhorro (
    idCuenta BIGINT PRIMARY KEY,
    tasaInteres DOUBLE,
    FOREIGN KEY (idCuenta) REFERENCES Cuenta(idCuenta)
);

CREATE TABLE CuentaCorriente (
    idCuenta BIGINT PRIMARY KEY,
    limiteSobregiro DOUBLE,
    FOREIGN KEY (idCuenta) REFERENCES Cuenta(idCuenta)
);
