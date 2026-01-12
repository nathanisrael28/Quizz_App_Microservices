list al the docker compse cmds

As of 2026,
Docker Compose commands are primarily used via the docker compose plugin rather than the deprecated docker-compose standalone tool. 
Core Lifecycle Commands

    up: Builds, (re)creates, and starts containers for a service.
    down: Stops and removes containers, networks, and images defined in the file.
    stop: Halts running containers without removing them.
    start: Restarts stopped containers for services.
    restart: Restarts running or stopped services.
    pause: Suspends all processes in the specified service containers.
    unpause: Resumes paused services. 

Management & Information

    ps: Lists containers and their current status.
    ls: Lists all running Compose projects on the host.
    logs: Displays log output from services.
    top: Displays the running processes of your services.
    images: Lists images used by the created containers.
    port: Prints the public port for a port binding. 

Development & Execution

    exec: Runs a command inside a running container.
    run: Runs a one-off command on a service.
    build: Builds or rebuilds services defined in the configuration.
    watch: (Newer feature) Automatically updates services when files in the build context change.
    cp: Copies files/folders between a service container and the local filesystem. 

Configuration & Utilities

    config: Validates and views the Compose file in a canonical format.
    create: Creates containers for a service but does not start them.
    pull: Pulls service images from a registry.
    push: Pushes service images to a registry.
    rm: Removes stopped service containers.
    kill: Force-stops service containers by sending a SIGKILL signal.
    version: Shows the Docker Compose version information. 

Advanced/Experimental

    alpha: Accesses experimental commands still in development.
    wait: Blocks until containers of specified services stop.
    publish: Publishes the Compose application (often used with cloud integrations). 

    Docker compose Commands | AWS Builder Center
    18 Dec 2024 — Here are some commonly used Docker Compose commands: * 👉 docker-compose up: This command creates and starts all the co...
    AWS Builder Center

docker compose - Docker Docs
Compose sets the project name using the following mechanisms, in order of precedence: The -p command line flag; The COMPOSE_PROJEC...
Docker Docs
Reference - Docker Docs
docker compose bridge transformations list. docker compose build. docker compose config. docker compose cp. docker compose create.
Docker Docs

Show all
give me full length cmds like examples in list form
Thinking
Searching
Thinking a little longer
In 2026, the
docker compose CLI (plugin-based) is the standard. Below are full-length command examples categorized by their role in the container lifecycle.
1. Lifecycle & Startup

    Run in background: docker compose up -d
    (Starts all services in detached mode so they run in the background).
    Build and start: docker compose up --build
    (Forces a rebuild of images before starting containers).
    Stop without deleting: docker compose stop
    (Shuts down the processes but keeps the container instances and networks intact).
    Start existing containers: docker compose start
    (Resumes containers that were previously stopped with the stop command).
    Full cleanup: docker compose down -v
    (Stops containers and removes them, along with networks and named volumes). 

2. Development & Debugging

    View live logs: docker compose logs -f --tail 100
    (Follows the log output of all services, showing only the last 100 lines).
    Run a shell in a service: docker compose exec webapp bash
    (Opens an interactive terminal inside a running container named "webapp").
    Check status: docker compose ps
    (Lists all containers associated with the current project and their status).
    One-off command: docker compose run --rm webapp python manage.py migrate
    (Runs a specific command in a new container and removes that container after it finishes). 

3. Image & Build Management

    Build only: docker compose build --no-cache
    (Builds or rebuilds services defined in the file without using previous cache layers).
    Pull latest images: docker compose pull
    (Downloads updated versions of images defined in your Compose file from a registry).
    List images: docker compose images
    (Displays the images used by the current project's containers). 

4. Advanced Utilities

    Validate config: docker compose config
    (Checks your docker-compose.yml for syntax errors and renders the final merged configuration).
    Live code sync (2026 feature): docker compose watch
    (Monitors your local source code and automatically updates or rebuilds containers on save).
    Scale services: docker compose up -d --scale worker=3
    (Starts three separate instances of the "worker" service).
    Force stop: docker compose kill
    (Immediately terminates containers with a SIGKILL instead of a graceful shutdown)