# Architecture decisions

## Core progression

An activity awards one EXP per minute. The backend calculates total EXP from immutable activity records, then selects the greatest configured `level_thresholds.required_total_exp` that does not exceed that total. This avoids progress counters drifting from the record history.

`world_unlocks` stores unlock metadata separately from the renderer. The API returns stable element keys; the frontend maps those keys to its CSS/SVG scene. Adding balance data or a visual stage therefore does not require changing activity history.

## Security

The browser never receives the JWT body: Spring places it in an HttpOnly `GW_AUTH` cookie. The API sets a readable CSRF cookie and the frontend sends it in `X-XSRF-TOKEN` on POST requests. All user-owned queries take the authenticated subject from Spring Security rather than accepting a user ID.

## Boundaries

Controllers validate HTTP DTOs, services own use-case and progression rules, repositories access persistence, and entities remain internal to the REST API. This keeps later additions such as missions or achievements from coupling to controllers.
