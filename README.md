# Laserfiche Repository API Client

Use the Laserfiche Repository API to access data in a Laserfiche repository. Import or export files, modify the repository folder structure, read and modify templates and field values, and more.

Documentation [Laserfiche Repository API](https://developer.laserfiche.com/libraries.html).

## How to contribute

The project is built using Maven. Any text editor or IDE that can work with Maven will be fine. At work, we use IDEs like IntelliJ and Eclipse. For more information on project structure and developer related inforamtion, see DEVELOPER.md.

### Generate the repository client

1. Download the `swagger-code-gen` command line tool. The repo for that library can be found [here](https://search.maven.org/search?q=a:swagger-codegen-cli).
2. From the root directory of this Git repository, run the command `java -jar swagger-codegen-cli-3.0.34.jar generate -i swagger.json -l java --artifact-id lf-repository-api-client --api-package com.laserfiche.repository.api.client --model-package com.laserfiche.repository.api.client.model --library retrofit2`

### Build, Test, and Package

Since we use Maven, any Mavan command should work properly. You can also take a look at the workflow file (`./workflow/main.yml`) for quick reference.