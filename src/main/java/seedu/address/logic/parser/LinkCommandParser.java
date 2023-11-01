package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseField;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.state.State;

/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {
    private final Model model;
    public LinkCommandParser(Model model) {
        this.model = model;
    }
    public LinkCommandParser() {
        this.model = null;
    }
    @Override
    public LinkCommand parse(String arguments) throws ParseException {
        if (model == null) {
            return staticParse(arguments);
        } else {
            return statefulParse(arguments);
        }
    }

    private LinkCommand statefulParse(String arguments) throws ParseException {
        assert model != null;
        State state = model.getState();
        Name studentName;
        Name lessonName;
        switch (state) {
        case STUDENT:
            if (model.getCurrentlyDisplayedPerson() == null) {
                throw new ParseException("No student is shown");
            }
            studentName = model.getCurrentlyDisplayedPerson().getName();
            lessonName = ParserUtil.parseName(arguments);
            return new LinkCommand(lessonName, studentName);
        case SCHEDULE:
            if (model.getCurrentlyDisplayedLesson() == null) {
                throw new ParseException("No lesson is shown");
            }
            studentName = ParserUtil.parseName(arguments);
            lessonName = model.getCurrentlyDisplayedLesson().getName();
            return new LinkCommand(lessonName, studentName);
        default:
            throw new ParseException("Link command is not available in this state" + state.toString());
        }
    }
    private LinkCommand staticParse(String args) throws ParseException {
        Name studentName = parseField("student", args, Name::of, false);
        Name lessonName = parseField("lesson", args, Name::of, false);
        return new LinkCommand(lessonName, studentName);
    }
}