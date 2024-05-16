package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Corinthians;

public class CorinthiansRepository {

	private List<Corinthians> ingressos;
	private File database;
	
	//sobreescrever o arquivo
		private void saveIngresso() {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))){
				for(Corinthians corinthians : ingressos) {
					String data = corinthians.getId() + ";" + corinthians.getJogo() + ";" + corinthians.getPlano() + ";" +
				corinthians.getDependentes() + ";" + corinthians.getSetor() ;
				writer.println(data);
				}
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				System.out.println("Arquivo database não encontrado!");
			}
		}
	public CorinthiansRepository() {
		this.database = new File("database-Corinthians.txt");
		this.ingressos = new ArrayList<>();
		loadIngressos();
	}
	
	// Carregar
	private void loadIngressos() {
		try (Scanner sc = new Scanner(database)){
			while(sc.hasNextLine()) {
				String [] data = sc.nextLine().split(";");
				if(data.length >= 4) {
					//0 -> id, 1-> nome, 2-> inicio,3-> fim
					Corinthians corinthians = new Corinthians();
					corinthians.setId(Integer.parseInt(data[0]));
					corinthians.setJogo(data[1]);
					corinthians.setPlano(data[2]);
					corinthians.setDependentes(Integer.parseInt(data[3]));
					corinthians.setSetor(data[4]);
					ingressos.add(corinthians);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Arquivo não encontrado, criando outro");
		}
	}
	
	public Corinthians getIngressoByid(int id) {
		for(Corinthians corinthians : ingressos) {
			if(corinthians.getId()== id) {
				return corinthians;
			}
		}
		return null;
	}
	
	public void updateIngresso(Corinthians updatedIngresso) {
		for (int i = 0; i < ingressos.size(); i ++) {
			if(ingressos.get(i).getId() == updatedIngresso.getId()) {
				ingressos.set(i, updatedIngresso);
				saveIngresso();
				break;
			}
		}
	}
	
	public List<Corinthians> buscarTodos(){
		return new ArrayList<>(ingressos);
	}
	
	public void deleteIngresso(int id) {
		//percorre todo o array, caso seja igual ele remove
		ingressos.removeIf(user -> user.getId() == id);
	}
	public void addIngresso(Corinthians corinthians) {
		corinthians.setId(getNextId());
		ingressos.add(corinthians);
		saveIngresso();
	}
	public int getNextId() {
		int maxId = 0;
		for(Corinthians corinthians : ingressos) {
			if(corinthians.getId() > maxId) {
				maxId = corinthians.getId();
			}
		}
		return maxId + 1;
	}
}
