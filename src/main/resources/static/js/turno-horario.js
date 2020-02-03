var Escola = Escola || {};

Escola.TurnoHorario = ( function(){
	
	function TurnoHorario(){
		this.addBtn = $('.js-btn-add');
		this.NomeInput = $('#horario-input');
		this.tabelaHorariosContainer = $('.js-tabela-horarios-container');
	} 
	
	TurnoHorario.prototype.iniciar = function() {
		'use strict';
		this.addBtn.on('click', onAdicionar.bind(this));
		
	}
	
	function onAdicionar(ev){
		event.preventDefault();
		var btn = $(ev.currentTarget);
		var url = btn.data('url');	
		
		var horario = {};
		horario.id = 0;
		horario.nome = this.NomeInput.val(); 		
		this.NomeInput.val('');
		
		var retorno = $.ajax({
			method: "POST",
			url: url,
			data: horario
		});				
		
		retorno.done(onItemAdicionado.bind(this));		
	}
	
	function onItemAdicionado(result) {
		this.tabelaHorariosContainer.html(result);		
	}
		
	return TurnoHorario;
	
}());


$(function() {
	
	var turnoHorario = new Escola.TurnoHorario();
	turnoHorario.iniciar();
	
});