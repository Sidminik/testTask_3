package ru.cft.shift;

import org.junit.jupiter.api.Test;
import ru.cft.shift.cli.ArgsParser;
import ru.cft.shift.cli.ArgsValidator;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.cft.shift.config.StatisticsMode;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserArgsTest {
    static Stream<Arguments> validArgumentsProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"in1.txt"}),
                Arguments.of((Object) new String[]{"in1.txt", "in2.txt"}),
                Arguments.of((Object) new String[]{"file1.txt", "-f", "-o", "out.txt"}),
                Arguments.of((Object) new String[]{"file1.txt", "file2.txt", "-a"}),
                Arguments.of((Object) new String[]{"-s", "-o", "/some/path", "-a", "-p",
                        "sample-", "in1.txt", "in2.txt"})
        );
    }

    static Stream<Arguments> notValidArgumentsProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"in1.txt", ".txt"})
        );
    }

    @ParameterizedTest
    @MethodSource("validArgumentsProvider")
    void parseValidArguments(String[] args) {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        parser.parse(args);
        validator.validate(parser);
        assertThat(validator.hasErrors()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notValidArgumentsProvider")
    void notValidArgumentsProvider(String[] args) {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        parser.parse(args);
        validator.validate(parser);
        assertThat(validator.hasErrors()).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void parseNullOrEmptyArguments_ThrowsException(String[] args) {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateInputArgs(args)
        );

        assertThat(exception.getMessage())
                .contains("Arguments array cannot be null. " +
                        "You must enter the file name. " +
                        "It is also possible to enter other arguments.");
    }

    @Test
    void parseValidArguments() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String path = File.separator + "some" + File.separator + "path";
        String[] args = {"-s", "-o", path, "-a", "-p", "sample-", "in1.txt", "in2.txt"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(parser.getCommandReaderNames()).containsExactly("in1.txt", "in2.txt");
        assertThat(parser.getCommandStatistics()).isEqualTo(StatisticsMode.SHORT);
        assertThat(parser.getCommandWriterOutputPath()).isEqualTo(path);
        assertThat(parser.getCommandWriterOutputPrefix()).isEqualTo("sample-");
        assertThat(parser.isCommandWriterAppend()).isTrue();
        assertThat(validator.hasErrors()).isFalse();
    }

    @Test
    void parseUnknownArgument() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String[] args = {"in1.txt", "-unknown"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(validator.hasErrors()).isTrue();
        assertThat(validator.getErrors()).containsExactly("Unknown argument: -unknown");
    }

    @Test
    void parseMissingOutputPath() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String[] args = {"in1.txt", "-o"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(validator.hasErrors()).isTrue();
        assertThat(validator.getErrors()).containsExactly("Output path is missing after -o");
    }

    @Test
    void parseMissingPrefix() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String[] args = {"in1.txt", "-p"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(validator.hasErrors()).isTrue();
        assertThat(validator.getErrors()).containsExactly("Prefix is missing after -p");
    }

    @Test
    void parseTwoStatisticFlags() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String[] args = {"in1.txt", "-s", "-f"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(parser.getCommandStatistics()).isEqualTo(StatisticsMode.FULL);
    }

    @Test
    void parseTwoStatisticFlagsReverse() {
        ArgsParser parser = new ArgsParser();
        ArgsValidator validator = new ArgsValidator();
        String[] args = {"in1.txt", "-f", "-s"};

        parser.parse(args);
        validator.validate(parser);

        assertThat(parser.getCommandStatistics()).isEqualTo(StatisticsMode.FULL);
    }
}