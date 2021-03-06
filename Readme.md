## Pacotes da aplicação
- `applicaton:` 
    - `main:` Pacote onde ficam os arquivos de aplicação
  do sistema responsáveis por carregar as instâncias dos objetos do dominio
    - `repository:` Repartição que encapsula as oprações de pseudo-persistência da aplicação usando hashMap
    - `controller:` Pacote responsável por armazenar os controllers que ligam o dominio da aplicação 
  com a view
    - `sockets:` Armazena as classes usadas durante o processo de socket do sistema, composto por uma classe de comando, um cliente e um servidor
    - `view:` Onde fica a incialização das cenas implementadas no pacote Resources 
- `domain:` 
    - `entities:` Pacote onde são encontradas as entidades do sistema que serão usadas nos casos de uso
    - `usecases:` Pacote composto por todos os casos de uso do sistema, fazendo todas as operações necessárias sem que seja necessário
  sujar a raiz das entidades

## Entidades do sistema
- `ambiente:` Responsável por armazenar um número determinado de itens que integem entre si, assim como compor como membro de um projeto
- `itens:` Componentes eletrônicos de um ambiente, populam a classe ambiente com suas restrições
- `projeto:` Escopo maior da aplicação, sendo responsável por armazenar ambientes

## Casos de uso
- `ambiente:` Local onde é realizado o CRUD dos ambientes, assim como definição das regras de negócio relacionadas a modificação e remoção de ambientes no sistema
- `item:` Local onde é realizado o CRUD dos componetes de um ambiente, seguindo as regras de negócio de canais e módulos presentes no documento de requisitos
- `projeto:` Local onde é realizado o CRUD dos projetos
- `utils:` Classes úteis para a validação, notificação, comunicação para persistência de dados assim como exceptions do sistema

Em resumo, implementei os requisitos funcionais e não funcionais que eram essenciais ao sistema e dei início ao processo de criação de telas e sockets da lista de requisitos desejáveis.

Para os sockets implementei uma estratégia de usar usecases de controle assim como uma classe utilitária para transportar objetos através dos sockets. Enquanto para a interface gráfica fiz apenas as telas e separei pacotes para armazenar controladores que processam essas informações coletadas nas views devido ao tempo de desenvolvimento.
