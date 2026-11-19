# Security notes

## Route rules

The security rules are defined in [`SecurityConfig`](../src/main/java/fr/ece/autoloc/config/SecurityConfig.java).

| Route | Access |
| --- | --- |
| `/api/public/**` | Everyone can access it. |
| `/api/admin/**` | Only users with the `ADMIN` role can access it. |
| Any other route | The user must be logged in. |

Examples from [`DemoController`](../src/main/java/fr/ece/autoloc/web/controllers/DemoController.java):

- `GET /api/public/ping` is public.
- `GET /api/hello` requires a valid username and password.
- `GET /api/admin/report` requires a valid username and the `ADMIN` role.

The application uses HTTP Basic authentication. The client sends its credentials with
each request, so HTTPS should be used in a real application.

## Authentication and authorization

- **Authentication** asks: “Who is the user?” The username and password are checked.
- **Authorization** asks: “What is this user allowed to do?” The user's role is checked
  after login.

For example, the `student` user is authenticated but does not have the `ADMIN` role,
so that user can access `/api/hello` but not `/api/admin/report`.

## Password encoding

Passwords must not be stored as plain text. If the database or application is exposed,
plain-text passwords can be used immediately.

This application uses `BCryptPasswordEncoder`. BCrypt creates a one-way hash with a
random salt. During login, Spring hashes the entered password and compares it with the
stored hash. The original password cannot be recovered from the hash, and the salt
makes identical passwords produce different hashes.

This is called password **hashing** or **encoding**, not encryption. Encryption can be
reversed with a key; password hashes should not be reversible.

## In-memory users

The users are created by `InMemoryUserDetailsManager`:

- `student` has the `USER` role.
- `admin` has the `ADMIN` role.

This is useful for demonstrations and tests, but it has important limitations. Users
are lost when the application restarts, there is no user registration or password
management, and it does not work well for multiple application instances. A real
application should store users and their BCrypt hashes in a persisted data store.
