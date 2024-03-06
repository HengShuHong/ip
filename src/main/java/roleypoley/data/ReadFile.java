package roleypoley.data;

import roleypoley.exception.RoleyPoleyFileException;
import roleypoley.task.Deadline;
import roleypoley.task.Event;
import roleypoley.task.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    public static void readFileToArrayList() throws RoleyPoleyFileException {
        try {
            Path myPath = Paths.get("./src/main/java/RoleyPoleyData.txt");
            List<String> taskList = Files.readAllLines(myPath, StandardCharsets.UTF_8);
            for (String line : taskList) {
                if (!line.trim().isEmpty()) {
                    convertTask(line);
                }
            }
        } catch (IOException e) {
            File textFile = new File("RoleyPoleyData.txt");
        }
    }


    public static void convertTask(String line) throws RoleyPoleyFileException {
        String[] identifyTaskType = line.split(" ");
        String description = line.substring("X | Y | ".length());
        boolean isDone = switch (identifyTaskType[2]) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new RoleyPoleyFileException("FileContentError");
        };

        switch (identifyTaskType[0]) {
        case "T":
                RoleyPoley.taskList.add(new Todo(description, isDone));
            break;
        case "D":
            if (!description.contains("(by:")) {
                throw new RoleyPoleyFileException("DeadLineFormatError");
            } else {
                RoleyPoley.taskList.add(new Deadline(description, isDone));
            }
            break;
        case "E":
            if (!description.contains("(from:") || !description.contains("to:")) {
                throw new RoleyPoleyFileException("EventFormatError");
            } else {
                RoleyPoley.taskList.add(new Event(description, isDone));
            }
            break;
        default:
            throw new RoleyPoleyFileException("FileContentError");
        }
    }
}