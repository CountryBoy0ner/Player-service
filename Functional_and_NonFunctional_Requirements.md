
# Functional Requirements

1. **Player Registration**  
   - The system must provide player registration with a unique `username` and `email`.
   - Email must be encrypted before saving to the database.
   - A `PlayerRegistered` event must be generated and sent to the message queue upon registration.

2. **Tournament Management**  
   - The system must allow the creation of tournaments with fields: name, start date, end date, status (`upcoming`, `ongoing`, `finished`).
   - Administrators can start a tournament, switching its status to active and initiating match creation.

3. **Match Scheduling**  
   - The system must automatically pair players into matches upon tournament start.
   - Each match must be saved in the `matches` table, and a `MatchScheduled` event must be sent to the message queue.

4. **Score Recording**  
   - The Score Service must accept and store match scores, updating the `scores` table accordingly.

5. **API for Data Access**  
   - The system must provide an API to retrieve data about tournaments, matches, players, and scores.
   - The API must support at least one GET endpoint.

6. **Inter-Service Communication via Queue**  
   - The system must implement an event bus with at least one producer and one consumer.
   - Example events: `PlayerRegistered`, `MatchScheduled`.

7. **Caching**  
   - The system must support caching match results in memory.
   - The cache must be invalidated when results are updated.

8. **Logging**  
   - All business events and errors must be logged and written to a file or queue in Azure Blob Storage.


# Non-Functional Requirements

1. **Security and Data Protection**  
   - Personal data (e.g., `email`) must be encrypted during storage and transmission.
   - Data access must be secured via API with authorization (if required).

2. **Performance and Scalability**  
   - The system must handle a high volume of simultaneous registrations and match processing without performance degradation.
   - The architecture must be designed for cloud deployment with horizontal scaling capabilities.

3. **Monitoring and Metrics**  
   - The system must provide the following endpoints:
     - `/health` — status of database and services.
     - `/ping` — simple service availability check.
     - `/metrics` — operational statistics (e.g., players, matches, errors).

4. **Availability and Reliability**  
   - The system must maintain at least 99.9% availability (as feasible within the cloud environment).
   - Critical operations must have automated tests with at least 60% code coverage.

5. **Deployment and Maintenance**  
   - The project must have a CI/CD pipeline including Docker image build, tests, and deployment to the cloud (e.g., AKS via GitHub Actions or Azure Pipelines).
   - Support for quick rollback of changes if necessary.

6. **Presentation and Demonstration**  
   - The project must be ready for demonstration in a 5-minute presentation format.

7. **Technological Constraints**  
   - The solution must be implemented in a cloud environment (Azure).
   - The system must follow a microservices architecture with API Gateway, Message Queue, and separate services.
