# Deploy Carbon Apps Locally with Maven Goal

# About
This Maven plugin facilitates local deployment of WSO2 Micro Integrator (MI) artifacts. It's tailored for multimodal projects integrated with the Common Application Platform (CAP) project.

# Usage

First, we are not in maven central yet, build and install the plugin locally using:
```
mvn clean package
```
Ensure to set default parameters before deployment (We look forward to auto extract these from parent pom):
You can override these with command line flags.
```.zshrc
export MI_PATH=<WSO2MI_LOCATION>/wso2mi-4.2.0/repository/deployment/server/carbonapps
export CAR_PATH=<YOUR_CAR_PATH>/target/<name>.car
```

Finally, To incorporate this plugin as an independent goal managed within your project's POM, add the following configuration:
```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>com.chakray</groupId>
                <artifactId>deploy-local</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <id>deploy-car</id>
                        <goals>
                            <goal>deploy-car</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </pluginManagement>
</build>

```
Manually execute the deployment goal using the following Maven command:
```shell
mvn com.chakray:deploy-local:1.0-SNAPSHOT:deploy-car
```
If you prefer automatic execution during mvn clean install, bind the goal to the install phase:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.chakray</groupId>
            <artifactId>deploy-local</artifactId>
            <version>1.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <id>deploy-car</id>
                    <phase>install</phase> <!-- Bind the goal to the install phase -->
                    <goals>
                        <goal>deploy-car</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```
Then each time you will run `mvn clean install` on your parent pom artifacts will automatically be deployed to WSO2MI

# Contributions Needed:

-[ ] Automatic construction of variables from parent attributes and passing them as default parameters in the POM.
-[ ] Deployment to Maven Central.
