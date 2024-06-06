package controller;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private ChoiceBox<String> jogo;

	@FXML
	private ChoiceBox<String> plano;

	@FXML
	private ChoiceBox<String> dependente;

	@FXML
	private ChoiceBox<String> setor;

	private String[] jogos = { "Corinthians x São Paulo", "Corinthians x América" };

	private String[] planos = { "Fiel digital", "Minha vida", "Minha história", "Meu amor", "Minha cadeira" };

	private String[] dependentess = { "1", "2", "3", "4", "5" };

	private String[] setores = { "Sul", "Norte", "Leste inferior", "Leste superior", "Oeste inferior",
			"Oeste superior" };

	private CorinthiansRepository repoCorinthians;
	
	@FXML
	private Button clearBtn;
	
	@FXML
	private Button addBtn;

	private ObservableList<Corinthians> data;

	@FXML
	public void initialize() {
		cJogo.setCellValueFactory(new PropertyValueFactory<>("Jogo"));
		cPlano.setCellValueFactory(new PropertyValueFactory<>("Plano"));
		cDependentes.setCellValueFactory(new PropertyValueFactory<>("Dependentes"));
		cSetor.setCellValueFactory(new PropertyValueFactory<>("Setor"));

		jogo.getItems().addAll(jogos);
		plano.getItems().addAll(planos);
		dependente.getItems().addAll(dependentess);
		setor.getItems().addAll(setores);

		data = FXCollections.observableArrayList();

		tableView.setItems(data);
		repoCorinthians = new CorinthiansRepository();
		carregarDadosSalvos();
		
		tableView.setOnMouseClicked(this::clickedWithMouse);			
	}
	
	private void clickedWithMouse(MouseEvent event) {
		Corinthians selectedIngresso = tableView.getSelectionModel().getSelectedItem();
		if(selectedIngresso != null) {
			jogo.setValue(selectedIngresso.getJogo());
            plano.setValue(selectedIngresso.getPlano());
            dependente.setValue(selectedIngresso.getDependentes());
            setor.setValue(selectedIngresso.getSetor());
            
            addBtn.setText("Editar");
            clearBtn.setText("Limpar");
		}
	}

	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database-Corinthians.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length >= 4) {
					Corinthians corinthians = new Corinthians();
					corinthians.setId(Integer.parseInt(parts[0]));
					corinthians.setJogo(parts[1]);
					corinthians.setPlano(parts[2]);
					corinthians.setDependentes(parts[3]);
					corinthians.setSetor(parts[4]);
					data.add(corinthians);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void adicionar() {
		Corinthians ingresso = new Corinthians();
		ingresso.setJogo(jogo.getValue() + "");
		ingresso.setPlano(plano.getValue() + "");
		ingresso.setDependentes(dependente.getValue() + "");
		ingresso.setSetor(setor.getValue() + "");
		if (jogo.getValue() == null) {
			msgErro();
		} else if (plano.getValue() == null) {
			msgErro();
		} else if (dependente.getValue() == null) {
			msgErro();
		} else if (setor.getValue() == null) {
			msgErro();
		} else {
			repoCorinthians.addIngresso(ingresso);
			msgAdd();
		}
		
		if(addBtn.getText().equals("cadastrar")) {
			repoCorinthians.addIngresso(ingresso);
		}else {
			Corinthians selectedIngresso = tableView.getSelectionModel().getSelectedItem();
			repoCorinthians.updateIngresso(selectedIngresso);
			selectedIngresso.setJogo(ingresso.getJogo());
			selectedIngresso.setPlano(ingresso.getPlano());
			selectedIngresso.setDependentes(ingresso.getDependentes());
			selectedIngresso.setSetor(ingresso.getSetor());
			tableView.refresh();
		}
	}

	public void msgErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Alerta");
		alert.setContentText("Campo vazio!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	public void msgAdd() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Sucesso");
		alert.setContentText("Ingresso comprado!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void limpar() {
		if (clearBtn.getText().equals("Limpar")) {
			clearFields();
		}else {
			Corinthians selectedIngresso = tableView.getSelectionModel().getSelectedItem();
			if(selectedIngresso != null) {
				data.remove(selectedIngresso);
				repoCorinthians.deleteIngresso(selectedIngresso.getId());
				clearFields();
			}
		}
	}

	public void clearFields() {
		jogo.getItems().addAll(jogos);
		plano.getItems().addAll(planos);
		dependente.getItems().addAll(dependentess);
		setor.getItems().addAll(setores);
	}

}
