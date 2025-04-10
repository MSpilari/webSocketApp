import axios from "axios";

const processSelectedFiles = (files: File[]) => {
  return files.map((file) => {
    return { file, previewImg: URL.createObjectURL(file) };
  });
};

const uploadAlbum = async (
  albumName: string,
  files: { file: File; previewImg: string }[]
) => {
  const formData = new FormData();
  formData.append("album_name", albumName);
  files.forEach((file) => {
    formData.append("images", file.file);
  });

  const response = await axios.post(
    "http://localhost:8080/api/v1/upload",
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );

  return response.data;
};

export { processSelectedFiles, uploadAlbum };
