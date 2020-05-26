package autotech;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AutoTechCarros extends CarroDAO {

	private JFrame frame;
	private JTextField anoVeiculo;
	private JTextField modeloVeiculo;
	private JTextField placaVeiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoTechCarros window = new AutoTechCarros();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public AutoTechCarros() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
		Connection conexao;
		conexao = Database.getConexao();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNomePag = new JLabel("Informa\u00E7\u00F5es do Ve\u00EDculo");
		lblNomePag.setForeground(Color.RED);
		lblNomePag.setBounds(140, 10, 167, 18);
		lblNomePag.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNomePag);
		
		JLabel tipoVeiculoLabel = new JLabel("Tipo de Ve\u00EDculo:");
		tipoVeiculoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		tipoVeiculoLabel.setBounds(10, 39, 94, 14);
		panel.add(tipoVeiculoLabel);
		
		JLabel tipoServicoLabel = new JLabel("Qual tipo de servi\u00E7o deseja: ");
		tipoServicoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		tipoServicoLabel.setBounds(10, 165, 159, 14);
		panel.add(tipoServicoLabel);
		
		JLabel ano = new JLabel("Ano do Ve\u00EDculo:");
		ano.setFont(new Font("Arial", Font.BOLD, 12));
		ano.setBounds(245, 76, 87, 14);
		panel.add(ano);
		
		JButton Enviar = new JButton("Enviar");
		Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Enviar.setForeground(Color.RED);
		Enviar.setFont(new Font("Arial", Font.BOLD, 12));
		Enviar.setBounds(169, 227, 103, 23);
		panel.add(Enviar);
		
		JLabel fabricanteLabel = new JLabel("Fabricante:");
		fabricanteLabel.setFont(new Font("Arial", Font.BOLD, 12));
		fabricanteLabel.setBounds(10, 118, 63, 14);
		panel.add(fabricanteLabel);
		
		JLabel placa = new JLabel("Placa do Ve\u00EDculo:");
		placa.setFont(new Font("Arial", Font.BOLD, 12));
		placa.setBounds(245, 39, 103, 14);
		panel.add(placa);
		
		JRadioButton Carro = new JRadioButton("Carro");
		Carro.setFont(new Font("Arial", Font.PLAIN, 11));
		Carro.setBounds(161, 35, 53, 23);
		panel.add(Carro);
		
		JRadioButton Moto = new JRadioButton("Moto");
		Moto.setFont(new Font("Arial", Font.PLAIN, 11));
		Moto.setBounds(105, 35, 109, 23);
		panel.add(Moto);
		
		JTextArea serviçoVeiculo = new JTextArea();
		serviçoVeiculo.setBounds(169, 160, 167, 39);
		panel.add(serviçoVeiculo);
		
		JCheckBox chckbxTermo = new JCheckBox("Todas as informa\u00E7\u00F5es acimas s\u00E3o verdadeiras");
		chckbxTermo.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTermo.setBounds(93, 206, 263, 23);
		panel.add(chckbxTermo);
		
		JList list = new JList();
		list.setBounds(214, 131, -60, -62);
		panel.add(list);

		// Exemplo de uso do DAO, instancias de objetos de interface nao devem ser chamadas de DAO e sim do tipo correspondente
		// ArrayList<Fabricante> fabricantes = FabricanteDAO.getFabricantes(conexao); // TODO: usar esse metodo para popular usando Combobox.setModel
		JComboBox FabricanteVeiculo = new JComboBox();
		FabricanteVeiculo.setModel(new DefaultComboBoxModel(new String[] {"Chevrolet", "Volkswagen", "Fiat", "Renault", "Ford", "Toyota", "Hyundai", "Jeep", "Honda", "Nissan", "Citro\u00EBn", "Mitsubishi", "Peugeot", "Chery", "BMW", "Mercedes-Benz", "Kia", "Audi", "Volvo", "Land Rover "}));
		FabricanteVeiculo.setBounds(83, 114, 108, 22);
		panel.add(FabricanteVeiculo);

		JLabel veiculoLabel = new JLabel("Modelo do Ve\u00EDculo:");
		veiculoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		veiculoLabel.setBounds(10, 76, 109, 14);
		panel.add(veiculoLabel);
		
		anoVeiculo = new JTextField();
		anoVeiculo.setBounds(351, 73, 73, 20);
		panel.add(anoVeiculo);
		anoVeiculo.setColumns(10);
		
		modeloVeiculo = new JTextField();
		modeloVeiculo.setBounds(121, 73, 87, 20);
		panel.add(modeloVeiculo);
		modeloVeiculo.setColumns(10);
		
		placaVeiculo = new JTextField();
		placaVeiculo.setBounds(351, 39, 73, 20);
		panel.add(placaVeiculo);
		placaVeiculo.setColumns(10);
		
		conexao.close(); 
	}
}
