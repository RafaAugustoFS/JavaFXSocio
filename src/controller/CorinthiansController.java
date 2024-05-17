package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Corinthians;
import repository.CorinthiansRepository;

public class CorinthiansController {

	@FXML
	private TableView<Corinthians> tableView;
	
	@FXML
	private TableColumn<Corinthians, String> cJogo;
	
	@FXML
	private TableColumn<Corinthians, String> cPlano;
	
	@FXML
	private TableColumn<Corinthians, String> cDependentes;
	
	@FXML
	private TableColumn<Corinthians, String> cSetor;
	
	@FXML
	private ChoiceBox<String>jogo;
	
	@FXML
	private ChoiceBox<String>plano;
	
	@FXML
	private ChoiceBox<String>dependente;
	
	@FXML
	private ChoiceBox<String>setor;
	
	private String[] jogos = {" ","Corinthians x São Paulo", "Corinthians x América"};
	
	private String[] planos = {" ","Fiel digital", "Minha vida", "Minha história", "Meu amor","Minha cadeira"};
	
	private String[] dependentess = {" ","1", "2","3","4","5"};
	
	private String[] setores = {" ","Sul", "Norte", "Leste inferior", "Leste superior","Oeste inferior", "Oeste superior"};
	
	private CorinthiansRepository repoCorinthians;
	
	@FXML
	public void initialize() {
		repoCorinthians = new CorinthiansRepository();
		
		jogo.getItems().addAll(jogos);
		plano.getItems().addAll(planos);
		dependente.getItems().addAll(dependentess);
		setor.getItems().addAll(setores);
	}
	
	public void adicionar() {
	 Corinthians ingresso = new Corinthians();
	 ingresso.setJogo(jogo.getValue() + "");
	 ingresso.setPlano(plano.getValue() + "");
	 ingresso.setDependentes(dependente.getValue() + "");
	 ingresso.setSetor(setor.getValue() + "");
	 repoCorinthians.addIngresso(ingresso);
	}
	public void limpar() {
		clearFields();
	}
	public void clearFields() {
			
		plano.getItems().addAll(planos);
		dependente.getItems().addAll(dependentess);
		setor.getItems().addAll(setores);
	}
	
	
}
