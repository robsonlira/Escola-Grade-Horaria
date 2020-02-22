var Escola = Escola || {};

'use strict';
Escola.TurnoHorario = ( function(){
	
	function TurnoHorario(){
		this.id = $('#id');
		this.nome = $('#nome');
		this.abrev = $('#abrev');
		this.addBtn = $('.js-btn-add');
		this.NomeInput = $('#horario-input');
		this.tabela = $('#tabela').children('tbody');
		this.tabelaHorariosContainer = $('.js-tabela-horarios-container');
	} 
	
	TurnoHorario.prototype.iniciar = function() {
		this.addBtn.on('click', onAdicionar.bind(this));		
	}
	
	function onAdicionar(ev){
		event.preventDefault();
		var btn = $(ev.currentTarget);
		var url = btn.data('url');	
		
		var turno = {};
		turno.id = this.id.val();
		turno.nome = this.nome.val();
		turno.abrev = this.abrev.val();
		
		var horario = {};
		horario.id = 0;
		horario.nome = this.NomeInput.val(); 			
		
		$.ajax({
			method: "POST",
			url: url,
			data: horario,
			beforeSend: function(){
			    $("#alert").removeClass("alert alert-success alert-dismissible fade show").text("");				
			},
			success: onSuccessAdd.bind(this),
			error: onErrorAdd
		});						
	}
	
	function onSuccessAdd(data) {
		console.log("SUCCESS: ", data);
		this.NomeInput.val('');
		
		var itens = '';
		for (i in data.result) {
			itens += '<tr><td>' + data.result[i].id + '</td>';
			itens += '<td>' + data.result[i].nome + '</td>';
			itens += '<td colspan="2"></td></tr>';
		}		
		this.tabela.html(itens);
	}
	
	function onErrorAdd(e) {
	    console.log("ERROR: ", e);
	    var erro = $.parseJSON(e.responseText);
	    //$("#alert").addClass("alert alert-danger alert-dismissible fade show").text("Erro: " + erro.msg)
	    Swal.fire({
			  icon: 'error',
			  title: 'Oops... Ocorreu um erro',
			  text: erro.msg
		});	
	}
		
	return TurnoHorario;
	
})();


$(function() {
	
	var turnoHorario = new Escola.TurnoHorario();
	turnoHorario.iniciar();
	
});