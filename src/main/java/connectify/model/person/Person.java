package connectify.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import connectify.commons.util.CollectionUtil;
import connectify.commons.util.ToStringBuilder;
import connectify.model.Entity;
import connectify.model.Note;
import connectify.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person extends Entity {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    private final Note note;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, phone, email, address, note, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.tags.addAll(tags);
    }

    /**
     * Returns the name of this person.
     * @return Name of this person
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the phone number of this person.
     * @return Phone number of this person
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns the email of this person.
     * @return Email of this person
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the address of this person.
     * @return Address of this person
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the note associated with this person.
     * @return Note associated with this person
     */
    public Note getNote() {
        return note;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && note.equals(otherPerson.note)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, note, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("note", note)
                .add("tags", tags)
                .toString();
    }

}
