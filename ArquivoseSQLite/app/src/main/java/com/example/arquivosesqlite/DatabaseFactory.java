package com.example.arquivosesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DatabaseFactory {
    SQLiteDatabase db;
    String BANCO = "banco.db";

    private Context ctx;
    public DatabaseFactory(Context ctx) {
        this.ctx = ctx;
    }

    public void criarBanco() {
        //Busca o SCRIPT
        String sql = "";
        try{
            InputStream in = ctx.getAssets().open("script.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String linha = "";
            while((linha = reader.readLine()) != null) {
                sql += linha+"\n";
            }

            db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
            db.execSQL(sql);
            db.close();
            Toast.makeText(ctx, "Base criada com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            System.out.println(e);
            Toast.makeText(ctx, "Erro ao criar a base!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTable() {
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE CONTATO");
        db.close();
        Toast.makeText(ctx, "Tabela excluida!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Contato> buscarDados(String nome) {
        ArrayList<Contato> contatos = new ArrayList<>();

        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        Cursor lines = db.query("contato", new String[] {"id, nome, email, telefone"}, "nome like ?", new String[]{"%"+nome+"%"}, null, null, "id");
        if(lines.moveToFirst()){
            do{
                Contato c = new Contato();
                c.setId(lines.getInt(0));
                c.setNome(lines.getString(1));
                contatos.add(c);
            }while(lines.moveToNext());
        }
        return contatos;
    }

    public Contato consultarContato(int id){
        Contato c = new Contato();

        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        Cursor linhas = db.query("contato", new String[]{"id", "nome", "email", "telefone"},
                "id = "+id+"", null, null, null, null);
        if(linhas.moveToFirst()) {
            do {
                c.setId(linhas.getInt(0));
                c.setNome(linhas.getString(1));
                c.setEmail(linhas.getString(2));
                c.setTelefone(linhas.getString(3));
            }while(linhas.moveToNext());
        }
        db.close();
        return c;
    }

    public long cadastrarContato(Contato contato) {
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("telefone", contato.getTelefone());
        try{
            db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
            long id = db.insert("contato", null, values);
            db.close();
            return id;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean alterarContato(Contato contato) {
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("telefone", contato.getTelefone());
        try{
            db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
            db.update("contato", values, "id="+contato.getId(), null);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean excluirContato(int id) {
        try{
            db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
            db.delete("contato", "id="+id, null);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
