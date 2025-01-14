package connectify.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.person.Person;
import connectify.model.person.PersonAddress;
import connectify.model.person.PersonEmail;
import connectify.model.person.PersonName;
import connectify.model.person.PersonNote;
import connectify.model.person.PersonPhone;
import connectify.model.person.PersonPriority;
import connectify.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String note;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("note") String note,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("priority") String priority) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.priority = priority;

    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        note = source.getNote().getContent();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        priority = source.getPriority().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonName.class.getSimpleName()));
        }
        if (!PersonName.isValidName(name)) {
            throw new IllegalValueException(PersonName.MESSAGE_CONSTRAINTS);
        }
        final PersonName modelName = new PersonName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonPhone.class.getSimpleName()));
        }
        if (!PersonPhone.isValidPhone(phone)) {
            throw new IllegalValueException(PersonPhone.MESSAGE_CONSTRAINTS);
        }
        final PersonPhone modelPersonPhone = new PersonPhone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonEmail.class.getSimpleName()));
        }
        if (!PersonEmail.isValidEmail(email)) {
            throw new IllegalValueException(PersonEmail.MESSAGE_CONSTRAINTS);
        }
        final PersonEmail modelPersonEmail = new PersonEmail(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonAddress.class.getSimpleName()));
        }
        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Note"));
        }
        final PersonNote modelNote = new PersonNote(note);
        if (!PersonAddress.isValidAddress(address)) {
            throw new IllegalValueException(PersonAddress.MESSAGE_CONSTRAINTS);
        }
        final PersonAddress modelPersonAddress = new PersonAddress(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonPriority.class.getSimpleName()));
        }
        final PersonPriority priority = new PersonPriority(this.priority);

        return new Person(modelName, modelPersonPhone, modelPersonEmail, modelPersonAddress, modelTags,
                modelNote, priority);
    }

}
