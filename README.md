# VSI Developer Test
Esse repositório contém as tarefas exigidas no teste técnico da VSI (Java).

O repositório contém a implementação das tarefas para facilitar o entedimento. Basta rodar a classe ``Main`` para ver output das questões, e rodar os testes unitários na pasta ``test``.


### 1. Questão
1. Write a Java program to solve the following problem:
   You are tasked with creating a utility function for a text-processing application. The
   function must generate all possible anagrams from a given group of distinct letters. For
   example, the input {a, b, c} should return the output: abc, acb, bac, bca, cab, cba.
   Additional Requirements:
1. The program should accept any group of distinct letters as input and produce the
   correct result.
2. Optimize for readability and clarity.
3. Include basic validation (e.g., ensure the input is non-empty and contains only
   letters).
4. Write unit tests to validate your function with at least three different test cases,
   including edge cases (e.g., input with a single letter or empty input).
5. Document your code clearly, explaining the logic for generating anagrams.

### 1. Resposta:
No pacote ``br.com.vsi.application.Anagram`` contém a implementação da funcionalidade de criar um anagrama.

Todos os testes unitários das tarefas estão presentes na pasta ``test``.

```java
package br.com.vsi.application;

import java.util.ArrayList;
import java.util.List;

public class Anagram {
    private List<String> anagramsList;

    /**
     * Create the anagram list
     * @param input The input list
     * @return The anagram list
     */
    public List<String> execute(List<String> input) {
        this.anagramsList = new ArrayList<>();

        // Perform validation
        this.validate(input);

        // Start the generation of anagram
        this.generateRecursive(input, new ArrayList<>(), new StringBuilder());

        return this.anagramsList;
    }

    // Validate the input and throws exception if invalid
    private void validate(List<String> input) throws IllegalArgumentException {
        // Check if is empty
        if (input.isEmpty()){
            throw new IllegalArgumentException("The list cannot be empty");
        }

        // Check if is letter
        for(String itData : input) {
            if (itData.length() > 1 || ! Character.isLetter(itData.charAt(0))) {
                throw new IllegalArgumentException("The value \"" + itData + "\" is not a letter");
            }
        }
    }

    /**
     * Recursive method to generate anagrams
     * @param input The input list
     * @param lettersUsed The state of letters which have been used in the current anagram
     * @param itAnagramBuffer The string builder of current anagram
     */
    private void generateRecursive(List<String> input, List<String> lettersUsed, StringBuilder itAnagramBuffer) {
        // If size of the buffer is equals the size of the input, then add it to the anagram list
        if (itAnagramBuffer.length() == input.size()) {
            this.anagramsList.add(itAnagramBuffer.toString());
        }

        // Iterate over each letter in the input
        for (String letter : input) {
            // Skip this letter if it has already been used in the current iteration
            if (lettersUsed.contains(letter)) {
                continue;
            }

            // Append the letter to string builder and mark it as used
            itAnagramBuffer.append(letter);
            lettersUsed.add(letter);

            // Call this method recursively with the updated state
            this.generateRecursive(input, lettersUsed, itAnagramBuffer);

            // Remove the last letter from the buffer and unmark it as used
            itAnagramBuffer.deleteCharAt(itAnagramBuffer.length() - 1);
            lettersUsed.remove(letter);
        }
    };
}
```

---

### 2. Question
1. Provide an example scenario where overriding the equals() method is necessary in Java.
   Explain the key considerations when implementing this method, such as ensuring it
   aligns with the hashCode() method. Include code examples if possible.

### 2. Response
O método hashCode() é utilizado para gerar um hash único para um objeto. Esse hash é utilizado para garantir que listas utilizando HashMap, HashSet e derivados não permitam duplicidade. No pacote ``br.com.vsi.presentation.console.OverrideHashCodeConsolePrint`` contém um exemplo do uso de um HashSet que não duplicou a entidade User porque contém o mesmo uuid.

Já o método equals() é usado para verificar se um objeto é igual a outro. Sobrescrever o equals é muito útil para permitir verificações se uma instância é semelhante a outra, permitindo que coloque a regra de verificação na implementação. No pacote ``br.com.vsi.presentation.console.OverrideEqualsConsolePrint`` contém o exemplo de uso.

Implementação de uma entidade que efetua o ``@override`` nos métodos ``hashCode()`` e ``equals()``: 
```java
package br.com.vsi.domain;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Check if user entity id is equals
     * @param obj Input object
     * @return Returns true if is equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User objAsUser = (User) obj;

        return this.getId().equals(objAsUser.getId());
    }

    /**
     * Generate hash code using user uuid
     * @return User entity hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id.toString());
    }
}
```

---

### 3. Question
Explain how you would use a design pattern to decouple your code from a third-party
library that might be replaced in the future. Describe the advantages and limitations of
your chosen approach, and provide a small code snippet illustrating its application.

### 3. Response

Existem diversos designs patterns para desacoplar o código da aplicação com o de bibliotecas terceirizadas. Alguns exemplos de arquitetura são o Hexagonal Architecture, Onion Architecture e a Clean Architecture. Eu gosto muito de utilizar a Clean Architecture e separar as camadas da aplicação, esse repositório está separado seguindo o básico desse design pattern como exemplo.

Para um exemplo prático da questão, temos o pacote ``br.com.vsi.infrastructure.storage.Storage``, é uma classe abstrata que simula uma classe para armazenamento de arquivos. A classe contém os métodos abstratos ``read(String path)`` e ``save()``.

No pacote ``br.com.vsi.infrastructure.storage.LocalStorageImpl`` temos a implementação da classe ``Storage`` para o armazenamento local, e no pacote ``br.com.vsi.infrastructure.storage.AwsS3StorageImpl`` a implementação da classe para armazenamento no serviço AWS S3.

Com isso, separamos a responsabilidade das implementações de bibliotecas terceiras com a aplicação fazendo com que a aplicação use apenas a classe ``Storage``, recebendo a implementação em sí via Dependency Inversion por exemplo.

```java
package br.com.vsi.infrastructure.storage;

public abstract class Storage {
    abstract void save();
    abstract void read(String path);
}
```

---

### 4. Question
Describe your experience with Angular, including its core features and use cases. Provide
   an example of a practical application where you used Angular and include a code snippet
   demonstrating a key feature, such as component communication, data binding, or
   service integration.

### 4. Response

Uma das aplicações Angular que trabalhei foi uma plataforma para cuidadores de idosos no Canadá chamada Envoyy, onde era possível que os cuidadores se cadastrassem e os familiares requisitassem o serviço pela plataforma.

Na pasta ``src/main/java/br/com/vs/presentationt/angular`` contém um projeto angular exemplificando o uso de um componente utilizando ``@Input`` e ``@Output``.
O decorator @Input informa que o componente recebe uma entrada de dados, e o @Output uma funçæo externa.
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  standalone: true,
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.css']
})
export class CounterComponent {
  @Input() counter!: number;
  @Output() increment = new EventEmitter<void>();
  @Output() decrement = new EventEmitter<void>();

  onIncrement() {
    this.increment.emit();
  }

  onDecrement() {
    this.decrement.emit();
  }
}
```


---

### 5. Question

Discuss the techniques you use to prevent SQL injection attacks in web applications.
Provide examples of code showing secure implementations, such as using parameterized
queries or ORMs. Mention any additional measures you take to secure the database
layer.

### 5. Response

Para evitar SQL Injection podemos utilizar queries parâmetrizadas, isso evita com que o usuário realize o input de um SQL, fazendo com que carácteres usados em SQL sejam escapados com barra.

Algumas ORMs como Spring JPA já abstraem o SQL de forma que impossibilite o SQL Injection.

Segue um exemplo da implementação de uma raw query parametrizada usando JDBC, essa query está protegida contra SQL Injection:

```java
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    
    void findByEmail(Connection connection, String email) {
        String query = "SELECT * FROM users WHERE email = ?";
       
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,userInput);
       
        ResultSet resultSet = preparedStatement.executeQuery();

       while (resultSet.next()) {
          // Execute an action...
       }
    }
```

---

### 6. Question
Describe the steps you would take to diagnose and improve the performance of a batch
process that interacts with a database and an FTP server. Explain how you would identify
bottlenecks, optimize database queries, improve logic execution, and enhance file
transfer efficiency. Provide examples of tools or techniques you would use during the
analysis.

### 6. Response
É necessário avaliar o caso em particular para entender onde está o gargalo do desempenho. Se for no banco de dados, é necessário avaliar as queries e seus meios de execução, já trabalhei em casos onde a falta de índice no banco estava causando extrema lentidão em determinadas consultas, e também em casos onde foi necessário reescrever queries que estavam sendo abstraidas pela ORM por queries escritas na mão, já que com a ORM o controle do que é executado no banco é fraco pela forte abstração as bibliotecas/frameworks.
Se o gargalo for na transferência de arquivos, avaliaria a forma que essa transferencia está sendo efetuada. Uma forma eficiênte de melhorar o desempenho é utilizar serviços de filas como SQS para a transferir chunks de arquivos de forma assíncrona e controlada, utilizar serviços de cache como o Redis para consultas onde podem ser cacheadas e economizar o tempo que levaria em consultar o banco de dados, e comprimir os arquivos para reduzir a utilização da banda da rede.

---

### 7. Question and Responses
Given the tables above, write the SQL query that:
a. Returns the names of all Salesperson that don’t have any order with Samsonic.
```sql
SELECT distinct s.name FROM salesperson s
INNER JOIN orders o ON o.salesperson_id = s.id
INNER JOIN customer c ON c.id = o.customer_id
WHERE
	o.id != 4;
```
b. Updates the names of Salesperson that have 2 or more orders. It’s necessary to add an
‘*’ in the end of the name.
```sql
UPDATE salesperson
SET 
	name = concat(name, '*') 
WHERE id IN (
	SELECT s.id from salesperson s
	JOIN orders o on s.id = o.salesperson_id
	GROUP BY s.id
	HAVING COUNT(o.id) >= 2
)
```

c. Deletes all Ssalesperson that placed orders to the city of Jackson.
```sql
DELETE FROM salesperson
WHERE id IN (
    SELECT DISTINCT s.id
    FROM salesperson s
    JOIN orders o ON s.id = o.salesperson_id
    join customer c ON c.id = o.customer_id
    WHERE c.city = 'Jackson'
)
```

d. The total sales amount for each Salesperson. If the salesperson hasn’t sold anything,
show zero.
```sql
SELECT 
  s.id, 
  s.name, 
  COALESCE(SUM(o.amount), 0) AS total_sales
FROM salesperson s 
LEFT JOIN orders o ON o.salesperson_id = s.id 
GROUP BY s.id 
```

---

### 8. Question and Responses
The customer has a system called XYZ and intends to start updates split into 3 phases.
The requirements for the first phase are as follows:

1. Enable new data entries in the system, which will serve as input for the second
   phase.

2. Implement functionality to create, update, delete, and search plants.
   - Plants should have the following attributes:
     - Code: Numeric only, mandatory, and unique.
     - Description: Alphanumeric, up to 10 characters, optional.
     - Only admin users can delete plants.

3. Ensure that the system prevents duplication of plant codes.

#### Task:
Based on the above information:

1. Write a use case or user story for this scenario, ensuring that it clearly addresses the
   requirements.
```
Para essa task, teremos duas tarefas importantes:

Permissões:
- O sistema deve ter regras de permissões de usuário, permitindo separar as funcionalidades de usuários comuns para usuários administradores.
- Os usuários devem poder criar, editar e visualizar uma planta.
- Os admins devem poder criar, editar, visualizar e excluir uma planta.

Planta:
- Uma planta obrigatoriamente deve possuir um código único
- Uma planta opcionalmente pode possuir uma descrição, sendo até 10 caractéres.
- As plantas não podem ser duplicadas.
```

2. Highlight any business rules or assumptions relevant to the solution.
```
- Somente usuários admins podem excluir plantas
- As plantas não podem ser duplicadas.
```
 
3. Describe any validations or security measures you would implement in the system.
```
Para a planta teremos as seguintes validações:
- Campo código:
  - Campo obrigatório
  - Apenas números
  - Campo único
  
- Campo descrição:
  - Campo opcional
  - Contém no máximo 10 caracteres.  
```

4. Suggest how you would test this functionality, including examples of edge cases.
```
Essas funcionalidades são regras do domínio e podem ser testadas com testes unitários e validadas em QA.

Casos de teste:
  - Permissões:
    - Testar a criação de uma planta com um usuário comum autenticado.
    - Testar a edição de uma planta com um usuário comum autenticado.
    - Testar a remoçæo de uma planta com um usuário comum autenticado, deve falhar.
    - Testar a visualização de uma planta com um usuário comum autenticado.
    - Testar a criação de uma planta com um usuário admin autenticado.
    - Testar a edição de uma planta com um usuário admin autenticado.
    - Testar a remoçæo de uma planta com um usuário admin autenticado, deve falhar.
    - Testar a visualização de uma planta com um usuário admin autenticado.
  - Planta:
    - Testar a criação de uma planta com código e descrição.
    - Testar a criação de uma planta apenas com código.
    - Testar a criação de uma planta com código duplicado, deve falhar.
    - Testar a criação de uma planta com descrição maior que 10 caracteres, deve falhar.
  
  - Casos extremos para testar nas permissões:
    - Um usuário admin já autenticado foi alterado para usuário comum, esse usuário não deve poder remover uma planta.

  - Casos extremos para testar na criação de plantas:
    - Croar uma planta com um código não numérico, o sistema deve validar amigavelmente.
    - Tentar excluir uma planta que não existe.
```