# Connectify User Guide

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences. In this version (v1.2), we have focused on delivering the essential features for a Minimum Viable Product (MVP): **adding contacts**, **deleting contacts**, and **listing contacts**. This guide will walk you through the core functionality of Connectify.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Adding Contacts](#adding-contacts)
  * [Deleting Contacts](#deleting-contacts)
  * [Listing Contacts](#listing-contacts)
  * [Exit](#exit)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
<br><br>
[More Details Coming Soon]
--------------------------------------------------------------------------------------------------------------------

## Features

### Adding Contacts

#### Command: `add`

The **add** command allows you to add new contacts to your Connectify database. Follow the format below to add contacts:

`add n/NAME e/EMAIL [t/TAG]…`

- `n/NAME`: Specify the name of the contact.
- `e/EMAIL`: Add the email address of the contact.
- `t/TAG`: Optionally, you can include tags to categorize your contacts. Tags help you organize your connections efficiently.

**Example:**

To add a contact named John Doe with the email address johndoe@example.com and tag them as "colleague," use the following command:

```
Add n/John Doe e/johndoe@example.com t/colleague
```

**Successful Output:**

```
Got it. I've added this contact:
John Doe
Email:
```

**Unsuccessful Output:**

```
Please provide a valid email address.
```

### Deleting Contacts

#### Command: `deletePerson`

The **deletePerson** command allows you to remove contacts from your Connectify database. To delete a contact, you need to specify the contact's index.

```
deletePerson INDEX
```

- `INDEX`: Provide the index of the contact you want to delete.

**Example:**

To delete the contact named John Doe at index 1, use the following command:
```
deletePerson 1
```

**Successful Output:**

```
Noted. I've removed this contact:
John Doe
Email:
```

**Unsuccessful Output:**

```
Please provide a valid index.
```

### Editing People
####  Command: `edit`

The `edit` command allows you to modify the details of a person in the address book by specifying the person's index and the fields you want to change.

##### Example Usage

To edit a person's information, use the following command format:

```
edit INDEX [c/COMPANY] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]
```
The fields are:
- `INDEX` is the index of the person in the displayed person list of a company that you want to edit. It must be a positive integer within the company.
- `[c/COMPANY]` (optional) allows you to specify the company that the person is associated with.
- `[n/NAME]` (optional) allows you to change the person's name.
- `[p/PHONE]` (optional) allows you to change the person's phone number.
- `[e/EMAIL]` (optional) allows you to change the person's email.
- `[a/ADDRESS]` (optional) allows you to change the person's address.
- `[t/TAG]` (optional) allows you to add or remove tags for the person.


**Successful Output:**

Suppose you want to edit the name, phone, and email of the person at index 1 of  company 1. You can use the following command:

```
edit 1 c/1 n/NewName p/98765432 e/newemail@example.com
```
This command will edit the person's name to "NewName," their phone number to "98765432," and their email to "newemail@example.com."
- If the operation is successful, you will receive the following output:
```
Edited Person: NewName
```

**Unsuccessful Output**

1. If you don't provide at least one field to edit, you will receive the following error message:
```
At least one field to edit must be provided.
```
2. If the specified index is invalid (not within the displayed person list or not a positive integer), you will get the following error message:
```
Invalid index provided.
```

3. If the specified company index is missing, you will get the following error message:
```
No company provided.
```

4. If you try to edit a person's details in a company that does not exist, you will receive:

```
Invalid company index provided.
```

5. If the specified company index or person index is out of bounds, you will get the following error message:
```
Invalid index provided.
```

6. If you try to edit a person's details to match another person in the address book, you will receive the following error message:
```
This person already exists in the address book.
```

### Listing All Entities
####  Command: `list`

The `list` command allows you to retrieve a list of all entities (both persons and companies) in the Connectify address book. This command is useful for obtaining an overview of all the entities you have stored.

##### Example Usage

To list all entities in the address book, simply use the following command:
```
list
```
This command has no additional parameters or options.

**Successful Output:**

Assuming you have entities stored in Connectify, when you execute the list command, you will receive the following output:
```
Listed all persons and companies
```
This message confirms that all persons and companies have been successfully listed.


**Unsuccessful Output**
1. If Connectify is empty, and there are no entities to list, you will receive the following message:
```
There are no entities in Connectify
```
This message indicates that there are no entities to display, so the operation cannot be completed until you add entities to Connectify.

### Listing All Companies
####  Command: `companies`
The `companies` command allows you to retrieve a list of all companies in Connectify. This command is particularly useful for obtaining an overview of all the companies you have stored.

##### Example Usage
To list all companies in Connectify, simply use the following command:
```
companies
```
This command has no additional parameters or options.

**Successful Output:**
Assuming you have companies stored in your Connectify address book, when you execute the companies command, you will receive the following output:
```
Listed all companies
```
This message confirms that all companies have been successfully listed.

**Unsuccessful Output**
1. If Connectify does not contain any companies, and there are no companies to list, you will receive the following message:
```
There are no companies in Connectify
```
This message indicates that there are no companies to display, so the operation cannot be completed until you add companies to Connectify.


### Listing All People
####  Command: `people`
The `people` command allows you to retrieve a list of all individuals (persons) in the Connectify. This command is especially useful for obtaining an overview of all the individuals you have stored.

##### Example Usage
To list all people in the address book, simply use the following command:
```
people
```
This command has no additional parameters or options.

**Successful Output:**
Assuming you have people stored in Connectify, when you execute the people command, you will receive the following output:
```
Listed all persons
```
This message confirms that all persons have been successfully listed.

**Unsuccessful Output**
1. If Connectify does not contain any individuals (persons), and there are no people to list, you will receive the following message:
```
There are no people in Connectify
```
This message indicates that there are no persons to display, so the operation cannot be completed until you add persons to Connectify.





### Exit

#### Command: `exit`

This command exits the program.

**Successful Output:**

```
Bye. Hope to see you again soon!
```

**Unsuccessful Output:**

```
That is not a valid command.
```

[More Features Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## FAQ

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Known issues

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action           | Format, Examples              |
|------------------|-------------------------------|
| **Add**          | `add n/NAME e/EMAIL [t/TAG]…` <br> e.g., `add n/John Doe |
| **DeletePerson** | `deletePerson INDEX` <br> e.g., `delete 1` |
| **List**         | `list`                        |
| **Exit**         | `exit`                        |

