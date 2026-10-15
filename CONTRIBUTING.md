# Contributing to Finora

Finora is a financial record keeping and cash flow planning tool built for small Nigerian businesses. This guide explains how Bashir and Abdulsamad work together on the project, from picking up an issue to merging a pull request.

## Project layout

The repository holds two applications.

- `backend/` contains the Spring Boot API, owned by Bashir.
- `frontend/` contains the Next.js application, owned by Abdulsamad.

Each application has its own README with setup instructions. Read the relevant one before you start working in that folder.

## Before you start any task

Every piece of work should start from an issue on the project board, not from an empty branch. If you think of something that needs doing, open an issue first and place it in the Backlog column. Move an issue to Ready only once it has clear acceptance criteria and nothing blocking it.

If a task depends on an API endpoint, agree on the request and response shape with the other developer before you start building against it. Record that agreement in the issue or in `docs/api-specification.md`.

## Branch naming

Create a new branch for every task. Do not commit directly to `main`.

Use this pattern:

```
type/area-short-description
```

- `type` is one of `feature`, `fix`, `chore` or `docs`.
- `area` is either `backend` or `frontend`.

Examples:

- `feature/backend-sale-entity`
- `feature/frontend-login-page`
- `fix/backend-payment-rounding`
- `chore/infra-github-actions`

## Commit messages

Write commit messages that describe what changed, not how you felt about it. Keep the first line short and use a prefix that matches the kind of change.

```
feat: add sale entity and repository
fix: correct rounding in payment allocation
test: add unit tests for receivable balance
docs: update backend setup instructions
```

## Opening a pull request

1. Make sure your branch is up to date with `main` before opening the pull request.
2. Open the pull request against `main` and give it a clear title.
3. In the description, explain what changed and how you tested it.
4. Reference the related issue using `Closes #issue-number`, so the issue closes automatically when the pull request merges.
5. Wait for a review from the other developer. You cannot merge your own pull request without their approval.
6. Once the automated checks pass and the pull request is approved, merge using squash and merge. This keeps the history on `main` clean and easy to read.
7. Delete the branch after merging.

## Definition of done

Before moving an issue to Done, confirm the following.

- The acceptance criteria in the issue have been met.
- The code is readable and placed in the right module or feature folder.
- Validation and authorization are enforced on the server, not only in the interface.
- Loading, empty, success and error states are handled where they apply.
- Relevant tests have been written and pass locally.
- No secrets, debug output or commented out code are left behind.
- Documentation has been updated if the change affects setup or behaviour.
- The pull request has been reviewed and merged.

## Handling secrets and environment variables

Never commit real passwords, API keys, database credentials or signing keys. Each application keeps an `.env.example` file that lists the required variable names without real values. Copy it to `.env` locally and fill in your own values. The `.env` file stays out of git through the `.gitignore` files in this repository.

If a secret is ever committed by mistake, do not just delete the file in a new commit. Treat the secret as exposed, change it at the source, and let the other developer know.

## Keeping in touch

A short daily check in is enough for a team of two. Mention what you finished, what you plan to work on next, and anything blocking you. If a shared decision needs to be made, such as a field name in an API response or a financial rule, write it down once both of you agree, rather than relying on memory.
