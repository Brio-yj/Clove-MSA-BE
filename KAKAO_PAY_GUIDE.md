# Kakao Pay Integration Guidelines

- Use `WebClient` from Spring WebFlux for communicating with Kakao Pay APIs.
- Set required headers such as `Authorization` and `Content-Type` using `headers` on the request.
- Prefer non-blocking flows; `block()` may be used when synchronous behavior is acceptable.
- Avoid using `RestTemplate` for new development.
