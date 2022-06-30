# Laserfiche Repository API Client Maven

Use the Laserfiche Repository API to access data in a Laserfiche repository. Import or export files, modify the repository folder structure, read and modify templates and field values, and more.

Documentation [Laserfiche Repository API](https://developer.laserfiche.com/libraries.html).

## How to contribute

Technically you could use any editors you like. But it's more convenient if you are using either IntelliJ or Eclipse. Here is a few useful commands for building and testing the app.

### Generate the repository client

1. Download the `swagger-code-gen` command line tool. The repo for that library can be found [here](https://search.maven.org/search?q=a:swagger-codegen-cli).
2. From the root directory of this Git repository, run the command `java -jar swagger-codegen-cli-3.0.34.jar generate -i lf-APIServer.json -l java --artifact-id com.laserfiche.api.client  --api-package com.laserfiche.api.client.apiserver --model-package com.laserfiche.api.client.model`

### Build, Test, and Package

See the `./workflow/main.yml`.