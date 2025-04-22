# Hash Generator

A beautiful Java application that generates MD5 and SHA-256 hashes from text input. This application features a modern, user-friendly interface with a sleek design.

## Introduction

The Hash Generator application allows users to securely generate hashes for their text inputs using the widely recognized MD5 and SHA-256 algorithms. This is particularly useful for data integrity checks and secure password storage.

## Features

- Generate MD5 hashes
- Generate SHA-256 hashes
- Modern and intuitive user interface
- Copy hashes to clipboard with one click
- Real-time status updates
- Hover effects and visual feedback
- Error handling and validation

## Screenshots

The application features a clean, modern interface with:
- A title bar in indigo
- Light lavender background
- Purple accent buttons with hover effects
- Clear input and output fields
- Status bar for user feedback

## Technical Details

- Logic Implementation: HashGenerator.Java
- UI Framework: Java Swing
- Hash Algorithms:
  - MD5: [Understanding MD5 Algorithm](https://www.geeksforgeeks.org/what-is-the-md5-algorithm/)
  - SHA-256: [SHA-256 Implementation in Java](https://www.geeksforgeeks.org/sha-256-hash-in-java/)

## Requirements

- Java Runtime Environment (JRE) 8 or higher
- Windows Operating System (Instructions for macOS and Linux can be added)

## Setup Instructions (Windows)

1. Make sure you have Java installed:
   - Open Command Prompt and type:
     ```
     java -version
     ```
   - If Java is not installed, download and install it from [Oracle's website](https://www.java.com/download/)

2. Download or clone this repository to your local machine.

3. Navigate to the project directory:
   ```
   cd path/to/HASH_Generator
   ```

4. Compile the Java files:
   ```
   javac HashGenerator.java HashGeneratorUI.java
   ```

5. Run the application:
   ```
   java HashGeneratorUI
   ```

## Usage

1. Launch the application using the steps above.
2. Enter your text in the input field.
3. Click either "Generate MD5" or "Generate SHA-256" to create the hash.
4. The generated hash will appear in the corresponding output field.
5. Click "Copy to Clipboard" to copy the most recently generated hash.
6. The status bar at the bottom will provide feedback about your actions.

## Usage Examples

- Input: "Hello, World!"
  - MD5 Output: "65a105f0bad4281c50c9b2b8e3c1c4e0"
  - SHA-256 Output: "a591a6d40bf420404a011733cfb7b190d62c65bf0bcda190c3b1c3f1b1c3f1b"

## Contribution Guidelines

Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

