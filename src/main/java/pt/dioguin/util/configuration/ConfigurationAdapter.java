package pt.dioguin.util.configuration;

import java.io.File;
import java.util.Scanner;

public interface ConfigurationAdapter<T> {

     T adapt(Scanner scanner);

}
