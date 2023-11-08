package connectify.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import connectify.commons.util.ToStringBuilder;
import connectify.model.company.Company;
import connectify.model.company.UniqueCompanyList;
import connectify.model.person.Person;
import connectify.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueCompanyList companies;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        companies = new UniqueCompanyList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCompanies(List<Company> companies) {
        this.companies.setCompanies(companies);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setCompanies(newData.getCompanyList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCompany(Company c) {
        companies.add(c);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company in
     * the address book.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireNonNull(editedCompany);

        companies.setCompany(target, editedCompany);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCompany(Company key) {
        companies.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companies", companies)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }


    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companies.contains(company);
    }

    public ObservableList<Company> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    /**
     * Sorts the address book by the given comparators.
     * @param companyComparator
     * @param personComparator
     */
    public void sort(Comparator<Company> companyComparator, Comparator<Person> personComparator) {
        companies.sort(companyComparator);
        persons.sort(personComparator);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }
        AddressBook otherAddressBook = (AddressBook) other;

        return companies.equals(otherAddressBook.companies)
                && persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}