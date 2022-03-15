package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_REGEX;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.util.SearchType;


public class BatchCommandParser implements Parser<BatchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BatchCommandParser
     * and returns an BatchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SEARCH_TYPE, PREFIX_REGEX);

        String commandInput = args.split("by/")[0];
        if (!argMultimap.arePrefixesPresent(PREFIX_SEARCH_TYPE, PREFIX_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    BatchCommand.MESSAGE_USAGE));
        }

        return new BatchCommand(
                commandInput.trim(),
                new SearchType(argMultimap.getValue(PREFIX_SEARCH_TYPE).get().toLowerCase()),
                argMultimap.getValue(PREFIX_REGEX).get()
        );
    }
}
