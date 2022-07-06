## Pacotes da aplicação
- `applicaton:` 
    - `main:` Pacote onde ficam os arquivos de aplicação
  do sistema responsáveis por carregar as instâncias dos objetos do dominio
    - `repository:` Repartição que encapsula as oprações de persistência da aplicação
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

