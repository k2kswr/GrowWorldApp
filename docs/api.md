# API overview

All endpoints are under `/api/v1`. Successful responses are JSON. Validation failures use `application/problem+json` and include a `fields` map when applicable.

| Endpoint | Auth | Description |
|---|---|---|
| `POST /auth/register` | CSRF | Create account and set JWT cookie |
| `POST /auth/login` | CSRF | Authenticate and set JWT cookie |
| `POST /auth/logout` | CSRF | Expire JWT cookie |
| `GET /auth/me` | JWT | Current account |
| `GET /auth/csrf` | Public | Initialize CSRF cookie/token |
| `GET /dashboard` | JWT | Progress, today, active days, world |
| `POST /activities` | JWT + CSRF | Create today's action |
| `GET /activities?page=0&size=20` | JWT | Paginated activity history |

`POST /activities` body: `{ "category": "STUDY", "durationMinutes": 30 }`. Categories are `STUDY`, `APP_DEVELOPMENT`, `READING`, `EXERCISE`, `STRENGTH_TRAINING`, `SLEEP`, and `OTHER`.
