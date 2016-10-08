package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created on 08/10/16.<br/>
 */
public class InputValidator {

    public static void validate(String[] args){
        // basic verifications
        if (args.length < 1 || args.length > 2) {
            throw new IllegalStateException("Usage: Level [path_input.json] [path_output_file.json]");
        }

        Objects.requireNonNull(args[0], "The argument must be non null");
        Path inputPath = Paths.get(args[0]);

        if (Files.isDirectory(inputPath) || !Files.isReadable(inputPath)) {
            throw new IllegalStateException("Bad file path provided: " + args[0]);
        }
    }
}
