package maven.demo;

public class Principal {
	
	public static void main(String[] args) {

			DAO dao = new DAO();
			
			dao.conectar();
			int resposta;
			do {
				int id;
				String nome, interpretes, genero;
				Playlist[] playlist = dao.getMusica();
				System.out.println("Escolha uma das opções do menu:");
				System.out.println("(1) Listar\n(2) Inserir\n(3) Excluir\n(4) Atualizar\n(5) Sair");
				resposta = MyIO.readInt();	
				switch (resposta) {
					case 1:
						System.out.println("====       Playlist       ==== ");		
						for(int i = 0; i < playlist.length; i++) {
							System.out.println(playlist[i].toString());
						}
						break;
						
					case 2:
						System.out.println("==== Insira as informações ==== ");			
						System.out.println("Id da música: ");	
						id = MyIO.readInt();
						System.out.println("Id da música: " + id);
						System.out.println("Nome: ");	
						nome = MyIO.readLine();
						System.out.println("Interpretes: ");
						interpretes = MyIO.readLine();
						System.out.println("Genêro: ");	
						genero = MyIO.readLine();
						Playlist musica = new Playlist (id, nome, interpretes, genero );
						
						if(dao.inserirMusica(musica) == true) {
							System.out.println("Inserção com sucesso -> " + musica.toString());
						}
						break;
						
					case 3:
						System.out.println("==== Insira as informações ==== ");		
						System.out.println("Id da música: ");	
						id = MyIO.readInt();
						dao.excluirMusica(id);
						
						break;
						
					case 4:
						System.out.println("==== Insira as informações ==== ");		
						System.out.println("Id da música: ");	
						id = MyIO.readInt();
						id--;
						System.out.println("Nome registrado:\n"+ playlist[id].getNome()+"\nNovo nome:");	
						nome = MyIO.readLine();
						System.out.println("Interpretes registrados:\n"+ playlist[id].getInterpretes()+"\nNovos interpretes:");
						interpretes = MyIO.readLine();
						System.out.println("Genêro:\n"+ playlist[id].getGenero()+"\nNovo genero:");	
						genero = MyIO.readLine();
						playlist[id].setNome(nome);
						playlist[id].setInterpretes(interpretes);
						playlist[id].setGenero(genero);
						dao.atualizarMusica(playlist[id]);
						
						break;
						
					case 5:
						break;
						
					default:
						System.out.println("Erro! Insira um valor entre 1 e 5");
				}
			}while (resposta != 5);		
			
			dao.close();
		}
}
