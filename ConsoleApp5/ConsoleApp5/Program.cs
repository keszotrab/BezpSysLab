using System;
using System.Security.Cryptography;
using System.Text;

class Example
{
    static string getMd5Hash(string input)
    {
        MD5 md5Hasher = MD5.Create();
        byte[] data = md5Hasher.ComputeHash(Encoding.Default.GetBytes(input));
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < data.Length; i++)
        {
            sBuilder.Append(data[i].ToString("x2"));
        }
        return sBuilder.ToString();
    }

    static bool verifyMd5Hash(string input, string hash)
    {
        string hashOfInput = getMd5Hash(input);
        StringComparer comparer = StringComparer.OrdinalIgnoreCase;
        if (0 == comparer.Compare(hashOfInput, hash))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    static void Main()
    {
        string source = "Tekst";
        string hash = getMd5Hash(source);
        Console.WriteLine("");
        Console.WriteLine("");

        string source2 = "";
        

        source2 = Console.ReadLine();
        //string hash2 = getMd5Hash(source2);


        if (verifyMd5Hash(source2, hash))
        {
            Console.WriteLine("Weryfikacja OK.");
        }
        else
        {
            Console.WriteLine("Weryfikacja niepoprawna.");
        }
    }
}