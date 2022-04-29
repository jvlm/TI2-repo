package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.MusicaDAO;
import model.Musica;
import spark.Request;
import spark.Response;


public class MusicaService {

	private MusicaDAO musicaDAO = new MusicaDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_LANCAMENTO = 3;
	
	
	public MusicaService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Musica(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Musica(), orderBy);
	}

	
	public void makeForm(int tipo, Musica musica, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umaMusica = "";
		if(tipo != FORM_INSERT) {
			umaMusica += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umaMusica += "\t\t<tr>";
			umaMusica += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/musica/list/1\">Nova Faixa</a></b></font></td>";
			umaMusica += "\t\t</tr>";
			umaMusica += "\t</table>";
			umaMusica += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/musica/";
			String name, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Música";
				buttonLabel = "Inserir";
				umaMusica += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
				umaMusica += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td>&nbsp;Posição: <input class=\"input--register\" type=\"text\" name=\"id\" placeholder=\"Insira a posição...\"></td>";
				umaMusica += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" placeholder=\"Nome da faixa\"></td>";
				umaMusica += "\t\t\t<td>Interprete(s): <input class=\"input--register\" type=\"text\" name=\"interpretes\" placeholder=\"Nome dos cantores\"></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td>&nbsp;Gênero: <input class=\"input--register\" type=\"text\" name=\"genero\" placeholder=\"Gênero musical\"></td>";
				umaMusica += "\t\t\t<td>Lançamento: <input class=\"input--register\" type=\"text\" name=\"lancamento\" placeholder=\"Ano de Lançamento\"></td>";
				umaMusica += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t</table>";
				umaMusica += "\t</form>";
			} else {
				action += "update/" + musica.getID();
				name = "Atualizar Música (ID " + musica.getID() + ")";
				buttonLabel = "Atualizar";
				umaMusica += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
				umaMusica += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td>&nbsp;Posição: "+ musica.getID() +"</td>";
				umaMusica += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ musica.getNome() +"\"></td>";
				umaMusica += "\t\t\t<td>Interprete(s): <input class=\"input--register\" type=\"text\" name=\"interpretes\" value=\""+ musica.getInterpretes() +"\"></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t\t<tr>";
				umaMusica += "\t\t\t<td>&nbsp;Gênero: <input class=\"input--register\" type=\"text\" name=\"genero\" value=\""+ musica.getGenero() +"\"></td>";
				umaMusica += "\t\t\t<td>Ano de Lançamento: <input class=\"input--register\" type=\"text\" name=\"lancamento\" value=\""+ musica.getLancamento() + "\"></td>";
				umaMusica += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
				umaMusica += "\t\t</tr>";
				umaMusica += "\t</table>";
				umaMusica += "\t</form>";
			}
			
		} else if (tipo == FORM_DETAIL){
			umaMusica += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umaMusica += "\t\t<tr>";
			umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Faixa (ID " + musica.getID() + ")</b></font></td>";
			umaMusica += "\t\t</tr>";
			umaMusica += "\t\t<tr>";
			umaMusica += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umaMusica += "\t\t</tr>";
			umaMusica += "\t\t<tr>";
			umaMusica += "\t\t\t<td>&nbsp;Posição: "+ musica.getID() +"</td>";
			umaMusica += "\t\t\t<td>Nome: "+ musica.getNome() +"</td>";
			umaMusica += "\t\t\t<td>Interprete(s): "+ musica.getInterpretes() +"</td>";
			umaMusica += "\t\t</tr>";
			umaMusica += "\t\t<tr>";
			umaMusica += "\t\t\t<td>&nbsp;Genêro: "+ musica.getGenero() +"</td>";
			umaMusica += "\t\t\t<td>Ano de Lançamento: "+ musica.getLancamento() +"</td>";
			umaMusica += "\t\t\t<td>&nbsp;</td>";
			umaMusica += "\t\t</tr>";
			umaMusica += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UMA-MUSICA>", umaMusica);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação das Músicas</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/musica/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/musica/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/musica/list/" + FORM_ORDERBY_LANCAMENTO + "\"><b>Ano de Lançamento</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Musica> musicas;
		if (orderBy == FORM_ORDERBY_ID) {                 	musicas = musicaDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		musicas = musicaDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_LANCAMENTO) {			musicas = musicaDAO.getOrderByLancamento();
		} else {											musicas = musicaDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Musica p : musicas) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getLancamento() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/musica/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/musica/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteMusica('" + p.getID() + "', '" + p.getNome() + "', '" + p.getLancamento() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-MUSICA>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		int id = Integer.parseInt(request.queryParams("id"));
		String nome = request.queryParams("nome");
		String interpretes = request.queryParams("interpretes");
		String genero = request.queryParams("genero");
		int lancamento = Integer.parseInt(request.queryParams("lancamento"));
		
		String resp = "";
		
		Musica musica = new Musica(id, nome, interpretes, genero, lancamento);
		
		if(musicaDAO.insert(musica) == true) {
            resp = "Música (" + nome + ") inserida!";
            response.status(201); // 201 Created
		} else {
			resp = "Música (" + nome + ") não inserida!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Musica musica = (Musica) musicaDAO.get(id);
		
		if (musica != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, musica, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Música " + id + " não encontrada.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Musica musica = (Musica) musicaDAO.get(id);
		
		if (musica != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, musica, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Música " + id + " não encontrada.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Musica musica = musicaDAO.get(id);
        String resp = "";       

        if (musica != null) {
        	//musica.setId(Integer.parseInt(request.queryParams("id")));
        	musica.setNome(request.queryParams("nome"));
        	musica.setInterpretes(request.queryParams("interpretes"));
        	musica.setGenero(request.queryParams("genero"));
        	musica.setLancamento(Integer.parseInt(request.queryParams("lancamento")));
        	musicaDAO.update(musica);
        	response.status(200); // success
            resp = "Faixa (ID " + musica.getID() + ") atualizada!";
        } else {
            response.status(404); // 404 Not found
            resp = "Faixa (ID \" + musica.getId() + \") não encontrada!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Musica musica = musicaDAO.get(id);
        String resp = "";       

        if (musica != null) {
            musicaDAO.delete(id);
            response.status(200); // success
            resp = "Faixa (" + id + ") excluída!";
        } else {
            response.status(404); // 404 Not found
            resp = "Faixa (" + id + ") não encontrada!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}