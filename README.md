# Flashcard Generator Application (Containerized)

---

## Prerequisites

1. **Install Docker:**  
   Ensure Docker is installed and running. You can download it from [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/).

---

## How to Use

### Step 1: Clone the Repository
```bash
git clone https://github.com/jocatanas47/flashcard-generator
cd flashcard-generator
```

### Step 2: Build and Run the Docker Containers
```bash
docker compose up --build
```

## Available HTTP Requests
### POST /register
register a new user  
payload example:
```json
{
    "username": "john_doe",
    "email": "john.doe@example.com",
    "password": "securePassword123"
}
```
command:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"username": "john_doe", "email": "john.doe@example.com", "password": "securePassword123"}' http://localhost:8080/register
```
### POST /user/load_dictionary
load a list of known words into the user's dictionary  
payload example:
```json
[
    "Hund", "Katze", "machen"
]
```
command:
```bash
curl -X POST -u username:password -H "Content-Type: application/json" -d '["apple", "banana", "cherry"]' http://localhost:8080/user/load_dictionary
```
### POST /user/process_sentences
process sentences to generate Anki flashcards based on the unknown words in the sentence  
payload example:
```json
[
    "Das ist ein Pferd.",
    "Ich möchte Bananen essen."
]
```
command:
```bash
curl -X POST -u username:password -H "Content-Type: application/json" -d '["This is an apple.", "I love eating bananas."]' http://localhost:8080/user/process_sentences
```
