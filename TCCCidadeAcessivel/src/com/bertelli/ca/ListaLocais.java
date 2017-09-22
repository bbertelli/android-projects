package com.bertelli.ca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;

import android.graphics.drawable.Drawable;

public class ListaLocais extends ExpandableListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_trampolim);

        // Declara os itens que serao usados para criar a ExpandableList
        final String NAME = "name";
        final String IMAGE = "image";
        final LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ArrayList<HashMap<String, String>> headerData = new ArrayList<HashMap<String, String>>();

        //Declara os Titulos de cada grupo
        final HashMap<String, String> hospitais = new HashMap<String, String>();
        hospitais.put(NAME, "Hospitais");
        headerData.add( hospitais );

        final HashMap<String, String> escolas = new HashMap<String, String>();
        escolas.put(NAME, "Escolas");
        headerData.add( escolas);
        
        final HashMap<String, String> supermercados = new HashMap<String, String>();
        supermercados.put(NAME, "Supermercados");
        headerData.add( supermercados);
        
        final HashMap<String, String> lazer = new HashMap<String, String>();
        lazer.put(NAME, "Áreas de Lazer");
        headerData.add( lazer);
        
        final HashMap<String, String> bancos = new HashMap<String, String>();
        bancos.put(NAME, "Bancos");
        headerData.add( bancos);
        
        final HashMap<String, String> restaurantes = new HashMap<String, String>();
        restaurantes.put(NAME, "Restaurantes");
        headerData.add( restaurantes);
        
        final HashMap<String, String> vagas = new HashMap<String, String>();
        vagas.put(NAME, "Vagas de Estacionamento");
        headerData.add( vagas);
        
        final HashMap<String, String> farmacias = new HashMap<String, String>();
        farmacias.put(NAME, "Farmácias");
        headerData.add( farmacias);
        
        final HashMap<String, String> servicos = new HashMap<String, String>();
        servicos.put(NAME, "Serviços");
        headerData.add( servicos);


        //Cria os objetos que vai receber os itens para cada grupo
        final ArrayList<ArrayList<HashMap<String, Object>>> childData = new ArrayList<ArrayList<HashMap<String, Object>>>();

        final ArrayList<HashMap<String, Object>> g0_hospitais_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g0_hospitais_data);

        final ArrayList<HashMap<String, Object>> g1_escolas_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g1_escolas_data);
        
        final ArrayList<HashMap<String, Object>> g2_supermercados_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g2_supermercados_data);
        
        final ArrayList<HashMap<String, Object>> g3_lazer_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g3_lazer_data);
        
        final ArrayList<HashMap<String, Object>> g4_bancos_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g4_bancos_data);
        
        final ArrayList<HashMap<String, Object>> g5_restaurantes_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g5_restaurantes_data);
        
        final ArrayList<HashMap<String, Object>> g6_vagas_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g6_vagas_data);
        
        final ArrayList<HashMap<String, Object>> g7_farmacias_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g7_farmacias_data);
        
        final ArrayList<HashMap<String, Object>> g8_servicos_data = new ArrayList<HashMap<String, Object>>();
        childData.add(g8_servicos_data);

        //Declara os itens que vai ter nos grupos
        
        //Aqui esta declarando apenas para o grupo de hospitais
        final HashMap<String, Object> g0c0 = new HashMap<String,Object>();
        g0c0.put(NAME, "Hospital Augusto de Oliveira Camargo"+"\nAvenida Francisco de Paula Leite, 399 - Jardim Santa Cruz");    
        g0c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g0c1 = new HashMap<String,Object>();
        g0c1.put(NAME, "Hospital Santa Ignes"+"\nAvenida Presidente Vargas, 1591 - Cidade Nova");    
        g0c1.put(IMAGE, getResources().getDrawable(R.drawable.ir)); 
                
        //Aqui adiciona todos os itens no objeto do grupo hospitais
        g0_hospitais_data.add(g0c0);
        g0_hospitais_data.add(g0c1);  
        
        
        //Aqui esta declarando apenas para o grupo de escolas
        final HashMap<String, Object> g1c0 = new HashMap<String,Object>();
        g1c0.put(NAME, "Faculdade Max Planck"+"\nRua Rêmulo Zoppi, 434 - Vila Georgina");    
        g1c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g1c1 = new HashMap<String,Object>();
        g1c1.put(NAME, "Polo Educacional"+"\nAv Bernardino Bonavita, 1309 - Jardim Santa Rita");    
        g1c1.put(IMAGE, getResources().getDrawable(R.drawable.ir)); 
                
        //Aqui adiciona todos os itens no objeto do grupo escolas
        g1_escolas_data.add(g1c0);
        g1_escolas_data.add(g1c1);
        
        
        //Aqui esta declarando apenas para o grupo supermercados
        final HashMap<String, Object> g2c0 = new HashMap<String,Object>();
        g2c0.put(NAME, "Supermercado Pague Menos"+"\nRua Três Marias, 736 - Cidade Nova II");    
        g2c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g2c1 = new HashMap<String,Object>();
        g2c1.put(NAME, "Supermercado Pão de Açúcar"+"\nAv. Pres. Vargas, 1264 - Cidade Nova");    
        g2c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g2c2 = new HashMap<String,Object>();
        g2c2.put(NAME, "Supermercado Carrefour"+"\nAvenida Presidente Kennedy, 404 - Cidade Nova I");    
        g2c2.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo supermercados
        g2_supermercados_data.add(g2c0);
        g2_supermercados_data.add(g2c1);
        g2_supermercados_data.add(g2c2);
        
        
        //Aqui esta declarando apenas para o grupo lazer
        final HashMap<String, Object> g3c0 = new HashMap<String,Object>();
        g3c0.put(NAME, "Bosque do Saber"+"\nRua Pedeneiras, 410 - Jardim do Sol");    
        g3c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g3c1 = new HashMap<String,Object>();
        g3c1.put(NAME, "Polo Shopping"+"\nAl. Filtros Mann, 670- Jardim Tropical");    
        g3c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));

        final HashMap<String, Object> g3c2 = new HashMap<String,Object>();
        g3c2.put(NAME, "Shopping Jaraguá"+"\nRua Quinze de Novembro, 1200 - Centro");    
        g3c2.put(IMAGE, getResources().getDrawable(R.drawable.ir));

        final HashMap<String, Object> g3c3 = new HashMap<String,Object>();
        g3c3.put(NAME, "Teatro CIAEI"+"\nAvenida Engenheiro Fábio Roberto Barnabé, 3665 - Jardim Ragina");    
        g3c3.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo lazer
        g3_lazer_data.add(g3c0);
        g3_lazer_data.add(g3c1);
        g3_lazer_data.add(g3c2);
        g3_lazer_data.add(g3c3);
        
        
        //Aqui esta declarando apenas para o grupo bancos
        final HashMap<String, Object> g4c0 = new HashMap<String,Object>();
        g4c0.put(NAME, "Banco ITAÚ"+"\nRua Candelária, 771 - Centro");    
        g4c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g4c1 = new HashMap<String,Object>();
        g4c1.put(NAME, "Banco do Brasil"+"\nRua Bernardino de Campos, 771 - Centro");    
        g4c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo bancos
        g4_bancos_data.add(g4c0);
        g4_bancos_data.add(g4c1);
        
        //Aqui esta declarando apenas para o grupo restaurantes
        final HashMap<String, Object> g5c0 = new HashMap<String,Object>();
        g5c0.put(NAME, "Churrascaria Rio Grandense"+"\nAvenida Engenheiro Fábio Roberto Barnabé, 2752 - Jardim Esplanada");    
        g5c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g5c1 = new HashMap<String,Object>();
        g5c1.put(NAME, "Restaurante Pezão"+"\nAvenida Presidente Kennedy, 571 - Cidade Nova I");    
        g5c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo restaurantes
        g5_restaurantes_data.add(g5c0);
        g5_restaurantes_data.add(g5c1);
        
        //Aqui esta declarando apenas para o grupo vagas
        final HashMap<String, Object> g6c0 = new HashMap<String,Object>();
        g6c0.put(NAME, "Vaga de Estacionamento Praça Prudente de Morais"+"\nRua Cerqueira Cesar - Centro");    
        g6c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g6c1 = new HashMap<String,Object>();
        g6c1.put(NAME, "Vaga no Início do Parque Ecológico"+"\nAvenida Engenheiro Fábio Roberto Barnabé");    
        g6c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo vagas
        g6_vagas_data.add(g6c0);
        g6_vagas_data.add(g6c1);
        
        //Aqui esta declarando apenas para o grupo farmacias
        final HashMap<String, Object> g7c0 = new HashMap<String,Object>();
        g7c0.put(NAME, "Droga Raia"+"\nAvenida Presidente Vargas, 1303 - Cidade Nova I");    
        g7c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g7c1 = new HashMap<String,Object>();
        g7c1.put(NAME, "Droga Treze"+"\nRua 13 de Maio, 614 - Centro");    
        g7c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo farmacias
        g7_farmacias_data.add(g7c0);
        g7_farmacias_data.add(g7c1);
        
        //Aqui esta declarando apenas para o grupo servicos
        final HashMap<String, Object> g8c0 = new HashMap<String,Object>();
        g8c0.put(NAME, "SAAE - Água e Esgoto"+"\nRua Bernardino de Campos, 799 - Centro");    
        g8c0.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g8c1 = new HashMap<String,Object>();
        g8c1.put(NAME, "Correios"+"\nPraça Prudente de Moraes, 101 - Centro ");    
        g8c1.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g8c2 = new HashMap<String,Object>();
        g8c2.put(NAME, "Delegacia de Polícia"+"\nRua Bernardino de Campos, 796 - Centro");    
        g8c2.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        final HashMap<String, Object> g8c3 = new HashMap<String,Object>();
        g8c3.put(NAME, "CPFL Energia"+"\nRua Almirante Tamandaré, 1077 - Cidade Nova");    
        g8c3.put(IMAGE, getResources().getDrawable(R.drawable.ir));
        
        //Aqui adiciona todos os itens no objeto do grupo servicos
        g8_servicos_data.add(g8c0);
        g8_servicos_data.add(g8c1);
        g8_servicos_data.add(g8c2);
        g8_servicos_data.add(g8c3);
        
        setListAdapter( new SimpleExpandableListAdapter(
                this,
                headerData,
                R.layout.group_row,
                new String[] { NAME },    // the names of the data
                new int[] { R.id.groupname }, 	// the text field to populate with the field data
                childData,
                0,
                null,
                new int[] {}
            ) {
                @Override
                public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                    final View v = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);

                    // Popula a View do Layout
                    ((TextView)v.findViewById(R.id.name)).setText( (String) ((Map<String,Object>)getChild(groupPosition, childPosition)).get(NAME) );
                    ((ImageView)v.findViewById(R.id.image)).setImageDrawable( (Drawable) ((Map<String,Object>)getChild(groupPosition, childPosition)).get(IMAGE) );

                    return v;
                }

                @Override
                public View newChildView(boolean isLastChild, ViewGroup parent) {
                     return layoutInflater.inflate(R.layout.expandable_list_item_with_image, null, false);
                }
            }
        );
        ExpandableListView list = (ExpandableListView) findViewById(android.R.id.list);
        list.setOnChildClickListener(new OnChildClickListener(){
        	public boolean onChildClick(ExpandableListView parent, View v,
        			int groupPosition, int childPosition, long id) {
        		Log.d("Valor do Item", "Group:"+groupPosition+", Child: "+childPosition);
        		
        		String gid = Integer.toString(groupPosition);
        		String cid = Integer.toString(childPosition);
        		
        			
        			Intent intent = new Intent(ListaLocais.this, GMapsActivity.class);
        			intent.putExtra("gid", gid);
        			intent.putExtra("cid", cid);
        			startActivity(intent);
        			//GMapsActivity g = new GMapsActivity();
        			//g.encontrarLocal(gid, cid);
        		
        		
        		return true;
        	}
        });
    }
}