# Grow World

日々の行動が小さな世界を育てる習慣化Webアプリです。MVPは、記録した分数をEXPに変換し、レベルに応じて草原から都市までを解放します。

## Stack and architecture

- Frontend: Next.js (TypeScript, App Router)
- Backend: Java 21, Spring Boot, Spring Security, REST
- Database: PostgreSQL and Flyway migrations
- Local runtime: Docker Compose

ブラウザはHttpOnly JWT Cookieで認証します。状態変更にはCSRFトークンを必要とし、APIはユーザーIDをクライアントから受け取りません。Docker環境ではNext.jsが `/api/v1` をSpring Bootへプロキシするため、ブラウザは3000番ポートだけを利用します。

## Run locally

1. `docker compose up --build`
2. Open `http://localhost:3000`

開発用のDB資格情報は `growworld` / `growworld` です。JWT secret は本番で必ず安全な値に置き換えてください。

## Tests

- Backend: `cd backend && ./mvnw test` (または `mvn test`)
- Frontend: `cd frontend && npm ci && npm test`

## Deployment direction

本番ではNext.jsとSpring BootをECR/ECS Fargateへ配置し、ALBで公開、PostgreSQLはRDSへ配置します。GitHub ActionsはOIDCでAWSロールを引き受け、長期AWSキーを保存しない構成を採用します。
