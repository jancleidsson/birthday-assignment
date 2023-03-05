## Assignment

Given a JSON file with a list of people and their dates of birth, write a program to print out the people whose birthday is today.

If a person was born on Feb 29th, then their birthday during a non-leap year should be considered to be Feb 28th.

Input sample file:

```
[
    ["Doe", "John", "1982/10/08"],
    ["Wayne", "Bruce", "1965/01/30"],
    ["Gaga", "Lady", "1986/03/28"],
    ["Curry", "Mark", "1988/02/29"]
]
```

You can use whichever programming language you like. The assignment should take 60 to 90 min. If it’s taking longer, consider whether you’re complicating things.

If you make any assumptions, trade-offs or de-prioritise features for timeliness, please document these decisions.

Your submission must have:

* Instructions to run the code

* Tests to check if your code is correct, robust and complete

* Edge cases handled and tested

* Iterative development, backed by commit history

* Modular, cohesive code with sensible separation of concerns

Bonus points for following Test-Driven Development.

Please do not overcomplicate the code. You don’t need a web framework, database or message queues for this submission. Keep it simple!

## Dependencies For Running
- Docker
- Docker Compose

## Running the Project
To run the project, follow the steps below:

- Clone this repository;
- Navigate to the root directory of the project;
- Replace the content of the people JSON file at: `src/main/resources/peopleList.json`;
- Run the following command to build the Docker container and start the application:
```
docker compose run app
```
* You can change the content of people's JSON file whenever you want before running the start application command.