package com.chakray;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Mojo(name = "deploy-car")
public class Main extends AbstractMojo {
    @Parameter(defaultValue = "${env.CAR_PATH}",  readonly = true)
    private File sourceFile;

    @Parameter(defaultValue = "${env.MI_PATH}",  readonly = true)
    private String miPath;

    @Override
    public void execute() throws MojoFailureException, MojoExecutionException {
        getLog().info(String.format("Deploying Car %s to MI %s ", sourceFile.getAbsolutePath(), miPath));

        if (sourceFile == null) {
            throw new MojoFailureException("Empty CAR build path");
        }
        if (miPath == null) {
            throw new MojoFailureException("Empty MI path");
        }

        Path sourcePath = sourceFile.toPath();
        Path targetDirectory = Paths.get(miPath);
        Path targetPath = targetDirectory.resolve(sourceFile.getName());

        try {
            Files.copy(sourcePath, targetPath, REPLACE_EXISTING);
            getLog().info(String.format("Successfully deployed : %s", targetPath.getFileName()));
        } catch (IOException e) {
            getLog().info(String.format("Failed to deploy CAR : %s", e.getMessage()));
            throw new RuntimeException(e);
        }

    }
}