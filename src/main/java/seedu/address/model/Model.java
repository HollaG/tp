package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;
import seedu.address.ui.Ui;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' schedule list file path.
     */
    Path getScheduleListFilePath();

    /**
     * Sets the user prefs' schedule list file path.
     */
    void setScheduleListFilePath(Path scheduleListFilePath);

    /**
     * Replaces schedule list data with the data in {@code scheduleList}.
     */
    void setScheduleList(ReadOnlySchedule scheduleList);

    /** Returns the ScheduleList */
    ReadOnlySchedule getScheduleList();

    /**
     * Returns the user prefs' schedule list file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' schedule list file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces schedule list data with the data in {@code scheduleList}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Shows the details of the given person.
     * The person must exist in the address book.
     */
    void showPerson(Person personToShow);
    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the schedule list.
     */
    boolean hasLesson(Lesson lesson);

    boolean hasLessonClashWith(Lesson lesson);

    Lesson getLessonClashWith(Lesson lesson);

    /**
     * Deletes the given lesson.
     * The lesson must exist in the schedule list.
     */
    void deleteLesson(Lesson target);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in the schedule list.
     */
    void addLesson(Lesson lesson);

    /**
     * Replaces the given lesson {@code target} with {@code editedLesson}.
     * {@code target} must exist in the schedule list.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the schedule list.
     */
    void setLesson(Lesson target, Lesson editedLesson);
    // NOTE: TO ADD FILTERED FILTEREDLESSONLIST METHODS HERE.
    /**
     * Shows the details of the given lesson.
     * The lesson must exist in the application.
     */
    void showLesson(Lesson lessonToShow); //TODO
    ObservableList<Lesson> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered lesson list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Lesson> predicate);

    /**
     * Links the Ui of the Application.
     */
    void linkUi(Ui ui);

    /**
     * Gets the current app state.
     */
    State getState();

    /**
     * Sets the app state.
     * @param state New app state
     */
    void setState(State state);

    /**
     * Checks if the given state is the same as the current app state.
     * @param state State to check against app
     */
    boolean sameState(State state);
}
