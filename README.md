# Microservicio - Seguimiento de Envíos Internacionales

Este microservicio permite consultar el estado de envíos con rutas GET.

## Requisitos

- MySQL corriendo con base de datos `envios_db`
- Usuario: `root`, Password: `root`

## Ejecutar

```bash
mvn spring-boot:run
```

## Endpoints

- `GET /envios`
- `GET /envios/{id}`
