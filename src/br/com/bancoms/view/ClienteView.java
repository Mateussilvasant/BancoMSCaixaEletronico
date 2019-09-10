package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class ClienteView extends StackPane {
    private HBox hboxView;
    private Button botaoDepositar;
    private Button botaoSacar;
    private Button botaoTransferencia;
    private Button botaoEmprestimo;
    private Button botaoExtrato;
    private Button botaoEncerrar;

    public ClienteView(ClienteController controller) {
        controller.view.labelTituloBar.setText(
                "Sistema Banco MS - Menu Principal - Seja Bem Vindo " + controller.clienteSessao.getNomeCompleto());

        Group grupo = new Group();

        hboxView = new HBox();
        hboxView.setAlignment(Pos.CENTER);
        hboxView.setSpacing(CaixaView.METRICS.getPX(0.0008));

        grupo.getChildren().add(hboxView);
        getChildren().add(grupo);

        controller.view.getChildren().add(this);
    }

    public void iniciarCorrenteView(ClienteController controller) {
        botaoDepositar = new Button();
        botaoDepositar.getStyleClass().add("buttonBanco");
        botaoDepositar.setOnAction(controller.menuDepositoAction());
        botaoDepositar.setGraphic(new ImageView("depositar.png"));

        botaoSacar = new Button();
        botaoSacar.getStyleClass().add("buttonBanco");
        botaoSacar.setGraphic(new ImageView("sacar.png"));
        botaoSacar.setOnAction(controller.menuSaqueAction());

        botaoTransferencia = new Button();
        botaoTransferencia.getStyleClass().add("buttonBanco");
        botaoTransferencia.setGraphic(new ImageView("transferencia.png"));

        botaoEmprestimo = new Button();
        botaoEmprestimo.getStyleClass().add("buttonBanco");
        botaoEmprestimo.setGraphic(new ImageView("emprestimo.png"));

        botaoExtrato = new Button();
        botaoExtrato.getStyleClass().add("buttonBanco");
        botaoExtrato.setGraphic(new ImageView("saldos.png"));

        hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato);
    }

    public void iniciarPoupancaView(ClienteController controller) {
        botaoDepositar = new Button("Depósito");
        botaoDepositar.setOnAction(controller.menuDepositoAction());
        botaoSacar = new Button("Saque");
        botaoTransferencia = new Button("Transferência");
        botaoExtrato = new Button("Extratos");
        botaoEncerrar = new Button("Encerrar");

        hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato, botaoEncerrar);
    }

}
