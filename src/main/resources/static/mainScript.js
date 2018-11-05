$(document).ready(function(){
   loadYear();
});

function loadYear(){
    var str="";
    var arr=[];
    $.ajax({
        url: 'http://localhost:5000/loadYears',
        type: 'GET',
        crossDomain: true,
        dataType: 'json',
        data:{
            format:"json"
        },
        success:function(data){
            
            for(var i=0;i<data.length;i++){
                str=str+"<option value = '" + data[i]+ " '>" + data[i] + " </option>";
            }
           $("#YearAppend").append(str);
        }
    });
  
}
function fetchReport(){
    var year = $("#YearAppend").val();
    $("#totalAmount").empty();
    $("#AmountFunded").empty();
    $("#TotalInvestors").empty();
    var BarGrapArray =[];
    var array1 = [];
    var array2 = [];
    var array3 = [];
    var array4 = [];
    var array5 = [];
    var array6 = [];
    var array7 = [];
    var str1="";
    var str2="";
    var str3="";
    $.ajax({
        url: 'http://localhost:5000/fetchData/'+year,
        type: 'GET',
        crossDomain: true,
        dataType: 'json',
        data:{
            format:"json"
        },
        success:function(data){
      
          str1=data.aggregateTotals.amountApplied;
          str2=data.aggregateTotals.amountFunded;
          str3=data.aggregateTotals.amountCommitedByInvestors;
          $("#totalAmount").append("$"+str1.toLocaleString());
          $("#AmountFunded").append("$"+str2.toLocaleString());
          $("#TotalInvestors").append("$"+str3.toLocaleString());
          var x = data.monthlyLoanVolume.monthlyLoanVolumeArray.length;
          var A = data.loansByCreditGrade.gradeMap.A.length;
          var B = data.loansByCreditGrade.gradeMap.B.length;
          var C = data.loansByCreditGrade.gradeMap.C.length;
          var D = data.loansByCreditGrade.gradeMap.D.length;
          var E = data.loansByCreditGrade.gradeMap.E.length;
          var F = data.loansByCreditGrade.gradeMap.F.length;
          var G = data.loansByCreditGrade.gradeMap.G.length;
          
        for(var i=0;i<A;i++){
            array1.push(data.loansByCreditGrade.gradeMap.A[i]);
            array2.push(data.loansByCreditGrade.gradeMap.B[i]);
            array3.push(data.loansByCreditGrade.gradeMap.C[i]);
            array4.push(data.loansByCreditGrade.gradeMap.D[i]);
            array5.push(data.loansByCreditGrade.gradeMap.E[i]);
            array6.push(data.loansByCreditGrade.gradeMap.F[i]);
            array7.push(data.loansByCreditGrade.gradeMap.G[i]);
        }

          for(var i=0;i<x;i++){
              
              BarGrapArray.push(data.monthlyLoanVolume.monthlyLoanVolumeArray[i]);
          }
       Bargraph(BarGrapArray);
        }
        

    });
}

function Bargraph(getData){
  
  var data = getData;
$("#chart").empty();
var w = 800;
var h = 450;
var margin = {
	top: 58,
	bottom: 100,
	left: 80,
	right: 40
};
var width = w - margin.left - margin.right;
var height = h - margin.top - margin.bottom;

var x = d3.scale.ordinal()
		.domain(data.map(function(entry){
			return entry.month;
		}))
		.rangeBands([0, width]);
var y = d3.scale.linear()
		.domain([0, d3.max(data, function(d){
			return d.value;
		})])
		.range([height, 0]);
var ordinalColorScale = d3.scale.category20();
var xAxis = d3.svg.axis()
			.scale(x)
			.orient("bottom");
var yAxis = d3.svg.axis()
			.scale(y)
			.orient("left");
var yGridlines = d3.svg.axis()
				.scale(y)
				.tickSize(-width,0,0)
				.tickFormat("")
				.orient("left");
var svg = d3.select("#chart").append("svg")
			.attr("id", "chart")
			.attr("width", w)
			.attr("height", h);
var chart = svg.append("g")
			.classed("display", true)
			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

function plot(params){
	x.domain(data.map(function(entry){
			return entry.month;
		}));
	y.domain([0, d3.max(data, function(d){
			return d.value;
		})]);

	this.append("g")
		.call(params.gridlines)
		.classed("gridline", true)
		.attr("transform", "translate(0,0)")
	//enter()
	this.selectAll(".bar")
		.data(params.data)
		.enter()
			.append("rect")
			.classed("bar", true);

	this.selectAll(".bar-label")
		.data(params.data)
		.enter()
			.append("text")
			.classed("bar-label", true);
	
	//update
	this.selectAll(".bar")
		.attr("x", function(d,i){
			return x(d.month);
		})
		.attr("y", function(d,i){
			return y(d.value);
		})
		.attr("height", function(d,i){
			return height - y(d.value);
		})
		.attr("width", function(d){
			return x.rangeBand();
		})
		.style("fill", function(d,i){
			return ordinalColorScale(i);
		});

	this.selectAll(".bar-label")
		.attr("x", function(d,i){
			return x(d.month) + (x.rangeBand()/2)
		})
		.attr("dx", 0)
		.attr("y", function(d,i){
			return y(d.value);
		})
		.attr("dy", -6)
		.text(function(d){
			return d.value;
		})

	//exit()
	this.selectAll(".bar")
		.data(params.data)
		.exit()
		.remove();

	this.selectAll(".bar-label")
		.data(params.data)
		.exit()
		.remove();

	this.append("g")
		.classed("x axis", true)
		.attr("transform", "translate(" + 0 + "," + height + ")")
		.call(params.axis.x)
			.selectAll("text")
				.style("text-anchor", "end")
				.attr("dx", -8)
				.attr("dy", 8)
				.attr("transform", "translate(0,0) rotate(-45)");

	this.append("g")
		.classed("y axis", true)
		.attr("transform", "translate(0,0)")
		.call(params.axis.y);

	this.select(".y.axis")
		.append("text")
		.attr("x", 0)
		.attr("y", 0)
		.style("text-anchor", "middle")
		.attr("transform", "translate(-50," + height/2 + ") rotate(-90)")
		.text("Units sold");

	this.select(".x.axis")
		.append("text")
		.attr("x", 0)
		.attr("y", 0)
		.style("text-anchor", "middle")
		.attr("transform", "translate(" + width/2 + ",80)")
		.text("Donut type");
}


plot.call(chart, {
	data: data,
	axis:{
		x: xAxis,
		y: yAxis
	},
	gridlines: yGridlines
});
}
