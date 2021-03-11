# MyTerminal

Wrapper para a biblioteca Lanterna (GUIs em modo texto, Java), usado para fins educacionais

## Instalação

Para usar esta biblioteca em um projeto Maven, edite o arquivo `pom.xml`, adicionando as linhas destacadas:

```xml
<project ...>

  <!-- Adicione este repositório -->
	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependencies>
    <!-- Aqui você pode ter várias dependências -->
    
    <!-- Adicione esta dependência -->
		<dependency>
			<groupId>com.github.rodrigorgs</groupId>
			<artifactId>myterminal</artifactId>
			<version>v1.1</version>
		</dependency>
	</dependencies>
</project>
```

## Uso

Um exemplo de uso da biblioteca se encontra no arquivo [MyTerminalAppHello.java](src/main/java/br/ufba/myterminal/examples/MyTerminalAppHello.java)

Consulte os comentários do arquivo [MyTerminal.java](src/main/java/br/ufba/myterminal/MyTerminal.java)
