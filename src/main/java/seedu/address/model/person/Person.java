package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private Name name;
    private Phone phone = Phone.DEFAULT_PHONE;
    private Email email = Email.DEFAULT_EMAIL;

    // Data fields
    private Address address = Address.DEFAULT_ADDRESS;
    private final Set<Subject> subjects = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    // ensure double navigation
    private final Set<Lesson> lessons = new HashSet<>();


    /**
     * Make sense to only force the name to be non-null
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Person(Name name, Phone phone, Email email, Address address, Set<Subject> subjects, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, subjects, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subjects.addAll(subjects);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    /**
     * Only update if arguement passes is not null. To ease the update and edit command
     */
    public void setPhoneIfNotNull(Phone phone) {
        if (phone != null) {
            this.phone = phone;
        }
    }

    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    /**
     * Only update if arguement passes is not null. To ease the update and edit command
     */
    public void setEmailIfNotNull(Email email) {
        if (email != null) {
            this.email = email;
        }
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Only update if arguement passes is not null. To ease the update and edit command
     */
    public void setAddressIfNotNull(Address address) {
        if (address != null) {
            this.address = address;
        }
    }
    /**
     * Returns an immutable subject set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }
    public void setSubjects(Set<Subject> subjects) {
        requireAllNonNull(subjects);
        this.subjects.clear();
        this.subjects.addAll(subjects);
    }

    public void setSubjectsIfNotNull(Set<Subject> subjects) {
        if (subjects != null) {
            this.subjects.clear();
            this.subjects.addAll(subjects);
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    public void setTags(Set<Tag> tags) {
        requireAllNonNull(tags);
        this.tags.clear();
        this.tags.addAll(tags);
    }
    public void setTagsIfNotNull(Set<Tag> tags) {
        if (tags != null) {
            this.tags.clear();
            this.tags.addAll(tags);
        }
    }

    /**
     * Returns an immutable lesson set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Lesson> getLessons() {
        lessons.removeIf(Lesson::isDeleted);
        return Collections.unmodifiableSet(lessons);
    }

    public void setLessons(Set<Lesson> lessons) {
        requireAllNonNull(lessons);
        this.lessons.clear();
        this.lessons.addAll(lessons);
    }

    public void setLessonsIfNotNull(Set<Lesson> lessons) {
        if (lessons != null) {
            this.lessons.clear();
            this.lessons.addAll(lessons);
        }
    }


    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && subjects.equals(otherPerson.subjects)
                && tags.equals(otherPerson.tags);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, subjects, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("subjects", subjects)
                .add("tags", tags)
                .toString();
    }

}
